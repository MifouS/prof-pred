const id = params.get('id');

if (id == '' || id == null)
    window.location.href = 'subject.html'

const breadcrumb = document.getElementById('breadcrumb');
const did = document.getElementById('id')
const subject_name = document.getElementById('subject_name')
const prf = document.getElementById('prf')
const updated = document.getElementById('updated')

fetch('http://localhost:8080/api/subject' + id)
    .then(rsp => {
        if (rsp.status === 200)
            return rsp.json()

        alert('Predmet nije pronadjen')
        window.location.href = './subject.html'
    })
    .then(data => {
        breadcrumb.innerText = `${data.subject_name}`
        did.value = data.id
        subject_name.value = data.subject_name

        fetch('http://localhost:8080/api/professor')
            .then(rsp => rsp.json())
            .then(categoryData => {
                categoryData.forEach(category => {
                    const option = document.createElement('option')
                    if(category.id === data.category.id) {
                        option.selected = true
                    }
                    option.value = professor.id
                    option.text = professor.name
                    prf.appendChild(option)
                })
            })

            updated.value = dateFormat(data.updatedAt)

            document.getElementById('save').addEventListener('click', () => {
                fetch(`http://localhost:8080/api/subject/${data.id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        subject_name: subject_name.value,
                        professorId: prf.value
                    })
                })
                .then(rsp=> {
                    if(rsp.ok) {
                        window.location.href = './subject.html'
                        return
                    }
                    alert(`Izmena predmeta nije bila uspešna (HTTP ${rsp.status})`)
                })
            })
    }) 