const table = document.getElementById('table')
const template = document.getElementById('template')

if (table && template) {
    showLoadingAnimation('We are retrieving the airline list for you.')
    axios('http://localhost:8080/api/airline')
        .then(rsp => {
            rsp.data.forEach(airline => {
                const copy = template.content.cloneNode(true)
                copy.querySelector('.id').innerText = airline.id
                copy.querySelector('.name').innerText = airline.name
                copy.querySelector('.country').innerText = airline.countryOfOrigin
                copy.querySelector('.btn').addEventListener('click', (e) => {
                    alert(airline.name)
                })
                table.appendChild(copy)
            })
            Swal.close()
        })
        .catch(error => {
            showErrorAlert()
        })
}
