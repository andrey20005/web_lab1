document.querySelectorAll('#calculate_form input[type="checkbox"]').forEach(box => {
    box.addEventListener('change', function() {
        if (this.checked) {
            // Снимаем выделение с других чекбоксов в той же группе
            document.querySelectorAll('#calculate_form input[type="checkbox"]').forEach(other => {
                if (other !== this) other.checked = false;
            });
        } else {
            this.checked = true
        }
    });
});

function setR(n) {
    for (i=0;i<2;i++) {
        document.querySelectorAll("#R")[i].textContent = n
        document.querySelectorAll("#R2")[i].textContent = n/2
        document.querySelectorAll("#mR")[i].textContent = -n
        document.querySelectorAll("#mR2")[i].textContent = -n/2
    }
}

document.querySelectorAll('#calculate_form input[name="R"]').forEach(rbox => {
    rbox.addEventListener('change', () => {
        if (rbox.checked) {
            setR(rbox.value);
        }
    })
})
setR(2);

document.getElementById("calculate_form").addEventListener(
    "submit",
    (ev) => {
        ev.preventDefault()
        const r = document.querySelector('#calculate_form input[name="R"]:checked').value;
        const x = document.querySelector('#calculate_form input[name="X"]:checked').value;
        const y = document.querySelector('#calculate_form input[name="Y"]').value.replace("+", "");
        url = "/calculate?r=" + r + "&x=" + x + "&y=" + y;
        console.log(url)
        fetch(url, {method: "POST"}).then(response => {
            console.log(response);
            if (!response.ok) {
                document.querySelector('#calculate_form button').setCustomValidity(response.statusText);
                document.querySelector('#calculate_form button').reportValidity()
                return;
            } else {response.json().then(result => {
                console.log(result)
                const row = document.getElementById("res_table").insertRow(1)
                row.insertCell(-1).appendChild(document.createTextNode(result.now_time))
                row.insertCell(-1).appendChild(document.createTextNode(result.r))
                row.insertCell(-1).appendChild(document.createTextNode(result.x))
                row.insertCell(-1).appendChild(document.createTextNode(result.y))
                row.insertCell(-1).appendChild(document.createTextNode(result.res))
            })}
        })
    }
)


