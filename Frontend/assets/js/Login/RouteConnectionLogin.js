document.getElementById("login_form").addEventListener("submit", async function(event) {
    event.preventDefault();  // Evita que a página seja recarregada

    // Captura os valores do formulário
    const email = document.getElementById("login_email").value;
    const password = document.getElementById("login_password").value;

    try {
        // Faz a requisição para o back-end
        const response = await fetch("http://localhost:8080/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                senha: password
            })
        });

        const data = await response.json();

        if (response.ok) {
            // Login bem-sucedido
            console.log("Usuário logado com sucesso", data);
            displayMessage("Login bem-sucedido!", "success");

            // Redirecionar após 2 segundos
            setTimeout(() => {
                window.location.href = "/Dados_Psicologo.html"; // Redirecione para a tela desejada
            }, 2000);
        } else {
            // Erro de login
            console.error("Erro no login", data);
            displayMessage("Credenciais inválidas.", "error");
        }
    } catch (error) {
        console.error("Erro de conexão", error);
        displayMessage("Erro ao tentar se conectar ao servidor.", "error");
    }
});

// Função para exibir mensagens
function displayMessage(message, type) {
    const messageElement = document.getElementById("message");
    messageElement.innerText = message;
    messageElement.className = type; // "success" ou "error"
    messageElement.style.display = "block";

    // Ocultar a mensagem após 3 segundos
    setTimeout(() => {
        messageElement.style.display = "none";
    }, 3000);
}
