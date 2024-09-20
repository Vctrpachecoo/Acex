document.getElementById('login_form').addEventListener('submit', async function (event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const Email = document.getElementById('login_email').value;
    const Senha = document.getElementById('login_password').value;
    const messageDiv = document.getElementById('message');

    try {
        const response = await fetch('https://localhost:7274/Login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ Email, Senha }),
        });

        if (response.ok) {
            const data = await response.json();
            messageDiv.textContent = data.message; // Exibe a mensagem de sucesso
            messageDiv.className = 'success'; // Classe de sucesso
            messageDiv.style.display = 'block'; // Exibe a mensagem
            
            setTimeout(() => {
                window.location.href = ''; // Redireciona após 2 segundos
            }, 2000); // Tempo em milissegundos
        } else {
            const error = await response.json();
            messageDiv.textContent = 'Erro: ' + error.message; // Exibe a mensagem de erro
            messageDiv.className = 'error'; // Classe de erro
            messageDiv.style.display = 'block'; // Exibe a mensagem
        }
    } catch (err) {
        console.error('Erro ao fazer login:', err);
        messageDiv.textContent = 'Erro ao conectar ao servidor.'; // Mensagem de erro
        messageDiv.className = 'error'; // Classe de erro
        messageDiv.style.display = 'block'; // Exibe a mensagem
    }
});
