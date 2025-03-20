const params = new URLSearchParams(location.search)
const card = document.getElementById('flight')

if (params.get('id') && card) {
    const id = params.get('id')

    showLoadingAnimation('We are retrieving the latest flight information.')
    client.get('/flight/' + id)
        .then(rsp => {
            const img = document.getElementById('img')
            img.src = `https://img.pequla.com/destination/${rsp.data.destination.split(' ')[0].toLowerCase()}.jpg`
            img.alt = rsp.data.destination
            document.getElementById('dest').innerText = rsp.data.destination
            document.getElementById('num').innerText = rsp.data.flightNumber
            document.getElementById('sch').innerText = new Date(rsp.data.scheduledAt).toLocaleString('sr-RS') 
            document.getElementById('plane').innerText = rsp.data.plane
            document.getElementById('gmaps').src = `https://www.google.com/maps?output=embed&q=${rsp.data.destination}`
            card.hidden = false
            Swal.close()
        })
        .catch(error => {
            showErrorAlert()
        })
}