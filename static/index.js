document.getElementById("get_time").addEventListener(
    "click",
    function () {
        fetch("/calculate").then(
            (response) => {
                response.text().then(
                    (res) => {
                        console.log(res);
                        document.getElementById("clock").textContent = res;
                    }
                )
            }
        )
    }
);
