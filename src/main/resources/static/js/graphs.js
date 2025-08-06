
function getRandomColor(index) {
    const colors = [
        'rgba(255, 99, 132, 0.6)',
        'rgba(54, 162, 235, 0.6)',
        'rgba(255, 206, 86, 0.6)',
        'rgba(75, 192, 192, 0.6)',
        'rgba(153, 102, 255, 0.6)',
        'rgba(255, 159, 64, 0.6)',
        'rgba(255, 159, 300, 0.6)'
    ];
    return colors[index % colors.length];
}

function createStackedBarGraph(canvasId, labels, datasetMap) {
    const datasets = [];

    Object.entries(datasetMap).forEach(([title, details], index) => {
        datasets.push({
            label: title,
            data: details,
            backgroundColor: getRandomColor(index)
        });
    });

    const ctx = document.getElementById(canvasId);
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: datasets,
        },
        options: {
            responsive: true,
            plugins: {
                title: { display: false }
            },
            scales: {
                x: { stacked: true },
                y: { stacked: true, beginAtZero: true }
            }
        }
    });
}

function createLineGraph(canvasId, labels, datasetMap) {
    const ctx = document.getElementById(canvasId).getContext('2d');

    const data = {
        labels: labels,
        datasets: [
            {
                label: 'Počet ryb',
                data: datasetMap,
                fill: false, // nevykreslí oblast pod čárou
                borderColor: 'rgba(75, 192, 192, 1)',
                tension: 0.3 // zakřivení čáry (0 = rovné spojnice)
            }
        ]
    };

    const options = {
        responsive: true,
        plugins: {
            title: {
                display: false
            },
            legend: {
                display: true,
                position: 'top'
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    };

    new Chart(ctx, {
        type: 'line', // Typ grafu
        data: data,
        options: options
    });
}
