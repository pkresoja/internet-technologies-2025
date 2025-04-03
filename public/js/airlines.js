const table = document.getElementById('table')
const template = document.getElementById('template')

if (table && template) {
    showLoadingAnimation('We are retrieving the airline list for you.')
    client.get('/airline')
        .then(rsp => {
            rsp.data.forEach(airline => {
                const copy = template.content.cloneNode(true)
                copy.querySelector('.id').innerText = airline.id
                copy.querySelector('.name').innerText = airline.name
                copy.querySelector('.country').innerText = airline.country
                copy.querySelector('.open').addEventListener('click', (e) => {
                    window.open(airline.website, '_blank')
                })
                copy.querySelector('.edit').addEventListener('click', (e) => {
                    window.location.href = './edit-airline.html?id=' + airline.id
                })
                copy.querySelector('.remove').addEventListener('click', (e) => {
                    if (confirm('Are you sure?')) {
                        client.request({
                            url: `/airline/${airline.id}`,
                            method: 'delete'
                        })
                        .then(rsp => window.location.reload())
                        .catch(e => showErrorAlert(`${e.code}: ${e.message}`))
                    }
                })
                table.appendChild(copy)
            })
            Swal.close()
        })
        .catch(e => showErrorAlert(`${e.code}: ${e.message}`))
}
