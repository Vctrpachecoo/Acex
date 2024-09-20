document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('cadastro_1_form');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Impede o envio padrão do formulário

        // Coleta os dados do formulário
        const formData = new FormData(form);
        const nome = formData.get('cadastro_1_nome_name');
        const email = formData.get('cadastro_1_email_name');
        const crp = formData.get('cadastro_1_crp_name');
        const especialidade = formData.get('cadastro_1_especialidade_name');
        const senha = formData.get('cadastro_1_password_name');
        const confirmaSenha = formData.get('cadastro_1_confirm_name');

         // Valida a senha
         const senhaValida = validatePassword(senha);
         if (!senhaValida.valid) {
             showMessage(senhaValida.message, 'error');
             return;
         }

        // Verifica se as senhas são iguais e se a senha é válida
        if (senha !== confirmaSenha) {
            exibirMensagem('As senhas não coincidem.', 'erro');
            return;
        }

        // Cria um objeto com os dados do psicólogo
        const psicologo = {
            nome: nome,
            email: email,
            crp: crp,
            especialidade: especialidade,
            senha: senha,
            confirmaSenha: confirmaSenha
        };

        try {
            // Envia a solicitação para a API
            const response = await fetch('https://localhost:7274/CadastroPsicologo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(psicologo)
            });

 // Verifica se a solicitação foi bem-sucedida
 if (response.ok) {
    showMessage('Psicologo cadastrado com sucesso!', 'success', () => {
        // Redireciona para a tela de login após 1 segundo
        setTimeout(() => {
            window.location.href = 'login.html'; // Substitua 'login.html' pela URL da sua tela de login
        }, 500);
    });
    form.reset(); // Limpa o formulário
} else {
    showMessage('Erro ao cadastrar novo psicologo.', 'error');
}
} catch (error) {
console.error('Erro:', error);
showMessage('Erro ao cadastrar novo psicologo.', 'error');
}
});

// Função para mostrar mensagens
function showMessage(message, type, callback) {
messageDiv.textContent = message;
messageDiv.className = `message ${type}`;
messageDiv.classList.remove('hidden');

// Remove a mensagem após 3 segundos, ou usa o callback se fornecido
setTimeout(() => {
messageDiv.classList.add('hidden');
if (callback) callback();
}, 3000);
}

// Função para validar a senha
function validatePassword(password) {
const minLength = 6;
const hasUpperCase = /[A-Z]/.test(password);
const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/.test(password);

if (password.length <= minLength) {
return { valid: false, message: 'A senha deve conter mais de 6 caracteres.' };
}
if (!hasUpperCase) {
return { valid: false, message: 'A senha deve conter pelo menos uma letra maiúscula.' };
}
if (!hasSymbol) {
return { valid: false, message: 'A senha deve conter pelo menos um símbolo.' };
}
return { valid: true, message: '' };
}
});



            