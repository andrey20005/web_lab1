// document.getElementById("get_time").addEventListener(
//     "click",
//     function () {
//         fetch("/calculate").then(
//             (response) => {
//                 response.text().then(
//                     (res) => {
//                         console.log(res);
//                         document.getElementById("clock").textContent = res;
//                     }
//                 )
//             }
//         )
//     }
// );


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
    async (ev) => {
        ev.preventDefault()
        const params = new URLSearchParams({
            "r": document.querySelector('#calculate_form input[name="R"]:checked').value,
            "x": document.querySelector('#calculate_form input[name="X"]:checked').value,
            "y": document.querySelector('#calculate_form input[name="Y"]').value
        })
        fetch("/calculate?" + params.toString()).then(
            (response) => {response.text().then((json) => {
                console.log(json)
                const data = JSON.parse(json)
                const table = document.getElementById("res_table")
                const row = document.createElement("tr")
                row.innerHTML = '<td>' + data.now_time + '</td>' +
                    '<td>'+ data.r + '</td>' +
                    '<td>' + data.x + '</td>' +
                    '<td>' + data.y + '</td>' +
                    '<td>' + data.res + '</td>'
                table.insertBefore(row, null)
            })}
        )
    }
)


