const params = new URLSearchParams(window.location)
document.getElementById('flight').innerText = params.get('id')