const table = document.getElementById('table')
const template = document.getElementById('template')

if (table && template) {
    showLoadingAnimation('We are retrieving the latest flight information.')
    client.get('/flight')
        .then(rsp => {
            rsp.data.forEach(flight => {
                const copy = template.content.cloneNode(true)
                copy.querySelector('.id').innerText = flight.id
                copy.querySelector('.dest').innerText = flight.destination
                copy.querySelector('.num').innerText = flight.flightNumber
                copy.querySelector('.sch').innerText = formatDate(flight.scheduledAt)
                copy.querySelector('.est').innerText = flight.estimatedAt ? formatDate(flight.estimatedAt) : 'On Time'
                copy.querySelector('.btn').addEventListener('click', (e) => {
                    const url = './details.html?id=' + flight.id
                    window.location.href = url
                    console.log(url)
                })
                table.appendChild(copy)
            })
            Swal.close()
        })
        .catch(e => showErrorAlert(`${e.code}: ${e.message}`))
}