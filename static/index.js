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

document.getElementById("calculate_form").addEventListener(
    "submit",
    (ev) => {
        ev.preventDefault()
        const params = new URLSearchParams({
            "r": document.querySelector('#calculate_form input[name="R"]:checked').value,
            "x": document.querySelector('#calculate_form input[name="X"]:checked').value,
            "y": document.querySelector('#calculate_form input[name="Y"]').value
        })
        fetch("/calculate?" + params.toString(), {method: "POST"}).then(response => {
            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }
            response.json().then(result => {
                console.log(result)
                const row = document.getElementById("res_table").insertRow(1)
                row.insertCell(-1).appendChild(document.createTextNode(result.now_time))
                row.insertCell(-1).appendChild(document.createTextNode(result.r))
                row.insertCell(-1).appendChild(document.createTextNode(result.x))
                row.insertCell(-1).appendChild(document.createTextNode(result.y))
                row.insertCell(-1).appendChild(document.createTextNode(result.res))
            })
        })
    }
)


