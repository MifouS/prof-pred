const subject_name = document.getElementById('subject_name')

fetch('http://localhost:8080/api/professor')
    .then(rsp => rsp.json())
    .then(data => {
        data.forEach(professor => {
            const option = document.createElement('option')
            option.value = professor.id
            option.text = professor.name + " " + professor.surname
            prf.appendChild(option)
        })
        document.getElementById('save').addEventListener('click', () => {
            if (subject_name.value == '' || subject_name.value == null) {
                alert('Ime ne sme da bude prazno.')
                return
            }

            fetch('http://localhost:8080/api/subject', {
                method: 'POST',
                headers: {
                    'Content-Type':'application/json'
                },
                body: JSON.stringify({
                    subject_name: subject_name.value,
                    professorId: prf.value
                })
            }).then(rsp => {
                if (rsp.ok) {
                    window.location.href = './subject.html'
                    return
                }
                alert(`Dodavanje knjige nije uspelo(HTTP ${rsp.status})`)
            })
        })
    })