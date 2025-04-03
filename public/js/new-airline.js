document.getElementById('data-form').addEventListener('submit', (e) => {
    e.preventDefault()
    client.request({
        url: '/airline',
        method: 'post',
        data: {
            name: document.getElementById('name').value,
            country: document.getElementById('country').value,
            website: document.getElementById('website').value
        }
    })
        .then(rsp => window.location.href = './airlines.html')
        .catch(e => showErrorAlert(`${e.code}: ${e.message}`))
})