const name = document.getElementById('name')
const surname = document.getElementById('surname')

document.getElementById('save').addEventListener('click', () => {
    if (name.value == '' || name.value == null) {
        alert('Ime ne sme da bude prazno.')
        return
    }

    if (surname.value == '' || surname.value == null) {
        alert('Prezime ne sme da bude prazno.')
        return
    }

    fetch('http://localhost:8080/api/professor', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            name: name.value,
            surname: surname.value,
        })
    }).then(rsp=> {
        if (rsp.ok) {
            window.location.href = './startPage.html'
            return
        }
        alert(`Dodavanje profesora nije bilo uspešno (HTTP ${rsp.status})`)
    })
})