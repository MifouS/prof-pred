const table = document.getElementById('professor-table')
const template = document.getElementById('professor')


fetch('http://localhost:8080/api/professor')
    .then(rsp => rsp.json())
    .then(data => {
        data.forEach(professor => {
            const copy = template.content.cloneNode(true)
            copy.querySelector('.id').innerText = professor.id
            copy.querySelector('.name').innerText = professor.name
            copy.querySelector('.surname').innerText = professor.surname
            copy.querySelector('.updated').innerText = dateFormat(professor.updated_at)
            copy.querySelector('.edit').href = `./editProfessor.html?id=${professor.id}`
            copy.querySelector('.remove').addEventListener('click', ()=> {
                if (confirm(`Zelite obrisati profesora ${professor.name}?`)) {
                    fetch(`http://localhost:8080/api/professor/${professor.id}`, {
                        method: 'DELETE',
                    })
                        .then(rsp=> {
                            if (rsp.status === 204) {
                                window.location.href = './startPage.html'
                                return
                            }
                            alert(`Brisanje profesora nije bilo uspesno (HTTP ${rsp.status})`)
                        })
                }
            })

            table.appendChild(copy)
        })
    })