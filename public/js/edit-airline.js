const params = new URLSearchParams(location.search)
const card = document.querySelector('.form-container')

if (params.get('id') && card) {
    const id = params.get('id')

    showLoadingAnimation('We are retrieving airline information.')
    client.get('/airline/' + id)
        .then(rsp => {
            document.getElementById('id').value = rsp.data.id
            document.getElementById('name').value = rsp.data.name
            document.getElementById('country').value = rsp.data.country
            document.getElementById('website').value = rsp.data.website
            document.getElementById('updated').value = formatDate(rsp.data.updatedAt ?? rsp.data.createdAt)
            card.hidden = false
            Swal.close()

            document.getElementById('data-form').addEventListener('submit', (e) => {
                e.preventDefault()
                client.request({
                    url: `/airline/${id}`,
                    method: 'put',
                    data: {
                        name: document.getElementById('name').value,
                        country: document.getElementById('country').value,
                        website: document.getElementById('website').value
                    }
                })
                    .then(rsp => window.location.href = './airlines.html')
                    .catch(e => showErrorAlert(`${e.code}: ${e.message}`))
            })
        })
        .catch(e => showErrorAlert(`${e.code}: ${e.message}`))
}