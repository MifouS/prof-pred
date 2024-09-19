const table = document.getElementById('subject-table')
const template = document.getElementById('subject')

fetch('http://localhost:8080/api/subject')
    .then(rsp => rsp.json())
    .then(data => {
        data.forEach(subject => {
            const copy = template.content.cloneNode(true)
            copy.querySelector('.id').innerText = subject.id
            copy.querySelector('.subject_name').innerText = subject.subject_name
            copy.querySelector('.professor').innerText = subject.professor.pname + " " + subject.professor.surname
            copy.querySelector('.updated').innerText = dateFormat(subject.updated_at)
            copy.querySelector('.edit').href = `./editSubject.html?id=${subject.id}`
            copy.querySelector('.remove').addEventListener('click', ()=> {
                if (confirm(`Zelite obrisati predmet ${subject.subject_name}?`)) {
                    fetch(`http://localhost:8080/api/subject/${subject.id}`, {
                        method: 'DELETE',
                    })
                        .then(rsp=> {
                            if (rsp.status === 204) {
                                window.location.href = './subject.html'
                                return
                            }
                            alert(`Brisanje predmeta nije bilo uspesno (HTTP ${rsp.status})`)
                        })
                }
            })

            table.appendChild(copy)
        })
    })