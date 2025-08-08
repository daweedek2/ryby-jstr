const stages = [
    { name: "člověk", emoji: "🧍", length: 180, color: "#f6e58d" },
    { name: "auto", emoji: "🚗", length: 400, color: "#7ed6df" },
    { name: "autobus", emoji: "🚌", length: 1200, color: "#f0932b" },
    { name: "žirafa", emoji: "🦒", length: 500, color: "#ffbe76" },
    { name: "kamion", emoji: "🚛", length: 1600, color: "#95afc0" },
    { name: "fotbalové hřiště", emoji: "⚽", length: 10000, color: "#55efc4" },
    { name: "Eiffelovka", emoji: "🗼", length: 32400, color: "#eb4d4b" },
    { name: "Karlův most", emoji: "🌉", length: 51600, color: "#dff9fb" },
    { name: "nejdelší vlak", emoji: "🚆", length: 73500, color: "#6ab04c" },
    { name: "Mount Everest", emoji: "🏔️", length: 884800, color: "#4834d4" },
    { name: "Maraton", emoji: "🏃", length: 421950, color: "#e056fd" }
];

function getStageInfo(cm) {
    let current = stages[0];
    let next = stages[stages.length - 1];

    for (let i = 0; i < stages.length; i++) {
        if (cm < stages[i].length) {
            next = stages[i];
            break;
        }
        current = stages[i];
    }

    const count = (cm / current.length).toFixed(1);
    const percentToNext = Math.min(100, ((cm - current.length) / (next.length - current.length)) * 100);
    return {
        emoji: current.emoji,
        label: `Ulovené ryby měří celkem ${cm} cm — to je asi ${count}× ${current.name}!`,
        progress: percentToNext.toFixed(1),
        nextTarget: next.name,
        color: current.color
    };
}

function addFishLengthInfo(totalLengthCm) {
    const result = getStageInfo(totalLengthCm);

    document.getElementById("fish-length-description").textContent = result.label;
    const bar = document.getElementById("progress-bar");
    bar.style.width = result.progress + "%";
    bar.style.backgroundColor = result.color;

    document.getElementById("length-legend").textContent =
        `Zbývá do další mety: ${result.nextTarget}`;

}

