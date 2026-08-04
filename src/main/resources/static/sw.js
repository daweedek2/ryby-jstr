const CACHE_NAME = 'jestrabice-v1';
const IMAGE_CACHE = 'jestrabice-images-v1';

// Soubory k přednačtení při instalaci
const STATIC_ASSETS = [
    '/',
    '/css/style.css', // uprav podle tvých cest k CSS/JS
    '/manifest.json',
    '/images/icon-192.png',
    '/images/icon-512.png'
];

// Instalace Service Workeru
self.addEventListener('install', (event) => {
    event.waitUntil(
        caches.open(CACHE_NAME).then((cache) => {
            console.log('[SW] Pre-caching static assets');
            return cache.addAll(STATIC_ASSETS);
        })
    );
    self.skipWaiting();
});

// Aktivace a promazání staré keše
self.addEventListener('activate', (event) => {
    event.waitUntil(
        caches.keys().then((keys) => {
            return Promise.all(
                keys.map((key) => {
                    if (key !== CACHE_NAME && key !== IMAGE_CACHE) {
                        console.log('[SW] Removing old cache:', key);
                        return caches.delete(key);
                    }
                })
            );
        })
    );
    self.clients.claim();
});

// Odchytávání síťových požadavků (Fetch)
self.addEventListener('fetch', (event) => {
    const request = event.request;
    const url = new URL(request.url);

    // Ignorovat ne-GET požadavky (např. odeslání nového úlovku přes POST)
    if (request.method !== 'GET') return;

    // 1. STRATEGIE PRO FOTKY: Cache First (Paměť -> Síť)
    if (request.destination === 'image' || url.pathname.includes('/uploads/')) {
        event.respondWith(
            caches.open(IMAGE_CACHE).then((cache) => {
                return cache.match(request).then((cachedResponse) => {
                    if (cachedResponse) {
                        return cachedResponse; // Vrátí okamžitě z keše
                    }
                    return fetch(request).then((networkResponse) => {
                        if (networkResponse && networkResponse.status === 200) {
                            cache.put(request, networkResponse.clone());
                        }
                        return networkResponse;
                    }).catch(() => {
                        // Náhradní obrázek / fallback pokud je offline a není v keši
                    });
                });
            })
        );
        return;
    }

    // 2. STRATEGIE PRO STRÁNKY A DATA: Network First (Síť -> Paměť jako záloha)
    event.respondWith(
        fetch(request)
            .then((networkResponse) => {
                if (networkResponse && networkResponse.status === 200) {
                    const responseClone = networkResponse.clone();
                    caches.open(CACHE_NAME).then((cache) => {
                        cache.put(request, responseClone);
                    });
                }
                return networkResponse;
            })
            .catch(() => {
                // Při výpadku signálu / offline režimu vytáhnout z keše
                return caches.match(request);
            })
    );
});