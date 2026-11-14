// Live (debounced) search for the admin list pages.
// Usage: liveSearch({ endpoint: '...', tbodyId: '...', inputs: [{id, param}, ...] })

function debounce(fn, delay) {
    let timer;
    return function () {
        clearTimeout(timer);
        const args = arguments;
        timer = setTimeout(function () { fn.apply(null, args); }, delay);
    };
}

function liveSearch(config) {
    const tbody = document.getElementById(config.tbodyId);
    if (!tbody) return;

    function run() {
        const params = new URLSearchParams();
        config.inputs.forEach(function (input) {
            const el = document.getElementById(input.id);
            if (el && el.value) {
                params.set(input.param, el.value);
            }
        });

        fetch(config.endpoint + '?' + params.toString())
            .then(function (res) {
                if (!res.ok) throw new Error('HTTP ' + res.status);
                return res.text();
            })
            .then(function (html) {
                tbody.innerHTML = html;
            })
            .catch(function () {
                // keep the current rows on error
            });
    }

    config.inputs.forEach(function (input) {
        const el = document.getElementById(input.id);
        if (!el) return;
        el.addEventListener('input', debounce(run, 300));
        el.addEventListener('change', run);
    });
}
