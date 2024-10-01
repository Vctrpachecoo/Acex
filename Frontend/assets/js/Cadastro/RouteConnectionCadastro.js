// PsicologoRoute.js

const form = document.getElementById('cadastro_1_form');
const messageDiv = document.getElementById('message');

form.addEventListener('submit', (event) => {
    event.preventDefault(); // Impede o envio padrão do formulário

    const formData = new FormData(form);
    const jsonData = {};

    // Converte os dados do formulário para um objeto JSON
    for (const [key, value] of formData.entries()) {
        jsonData[key.replace('_name', '')] = value; // Remove "_name" dos nomes dos campos
    }

    // Verifica se o usuário é psicólogo para incluir CRP e especialidade
    if (jsonData.tipo === 'paciente') {
        delete jsonData.crp;
        delete jsonData.especialidade;
    }

    // Faz a requisição POST para a API
    fetch('http://localhost:8080/users/registerUsers', { // Substitua pela URL correta da sua API
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })



    // Validações de sucesso ou erro (mensagem na tela)
    .then(data => {
        messageDiv.classList.add('success');
        messageDiv.textContent = 'Usuário cadastrado com sucesso!';
        messageDiv.style.display = 'block'; // Exibe a mensagem
        form.reset();
    
        setTimeout(() => {
            messageDiv.style.display = 'none'; // Oculta após 2 segundos
            window.location.href = "/Pages/LoginUsuario.html"; // Redireciona (opcional)
        }, 2000); 
    })
    .catch(error => {
        messageDiv.classList.add('error');
        messageDiv.textContent = error.message;
        messageDiv.style.display = 'block';
    
        setTimeout(() => {
            messageDiv.style.display = 'none'; 
        }, 2000);
    })
});