const client = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Accept': 'application/json',
        'X-Time': new Date().getTime()
    },
    validateStatus: (num) => {
        return num === 200 || num === 204
    }
})

const footer = document.getElementById('footer')
if (footer)
    footer.innerHTML = `&copy; ${new Date().getFullYear()} Singidunum University - Internet Technologies and Web Services`

function formatDate(iso) {
    return new Date(iso).toLocaleString('sr-RS')
}

function showLoadingAnimation(msg) {
    Swal.fire({
        title: 'Please wait!',
        text: msg,
        allowOutsideClick: false,
        showConfirmButton: false,
        showCancelButton: false,
        customClass: {
            popup: 'bg-dark text-light',
            title: 'text-white',
            content: 'text-light',
            htmlContainer: 'text-light',
            footer: 'text-muted'
        },
        didOpen: () => {
            Swal.showLoading()
        }
    })
}

function showErrorAlert(msg = 'There was an issue fetching the data.') {
    Swal.close()
    Swal.fire({
        icon: 'error',
        title: 'An error occured',
        text: msg,
        customClass: {
            confirmButton: 'btn btn-sm btn-primary',
            popup: 'bg-dark text-light',
            title: 'text-white',
            content: 'text-light',
        }
    })
}