document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('cadastro_2_form');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Impede o envio padrão do formulário

        // Coleta os dados do formulário
        const nome = document.getElementById('cadastro_2_nome').value;
        const email = document.getElementById('cadastro_2_email').value;
        const senha = document.getElementById('cadastro_2_password').value;
        const confirmaSenha = document.getElementById('cadastro_2_confirm').value;

        // Valida a senha
        const senhaValida = validatePassword(senha);
        if (!senhaValida.valid) {
            showMessage(senhaValida.message, 'error');
            return;
        }

        // Verifica se as senhas são iguais
        if (senha !== confirmaSenha) {
            showMessage('As senhas não coincidem.', 'error');
            return;
        }

        // Cria um objeto com os dados do paciente
        const paciente = {
            nomeCompleto: nome,
            email: email,
            senha: senha,
            confirmaSenha: confirmaSenha
        };

        try {
            // Envia a solicitação para a API
            const response = await fetch('https://localhost:7274/CadastroPaciente', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(paciente)
            });

            // Verifica se a solicitação foi bem-sucedida
            if (response.ok) {
                showMessage('Paciente cadastrado com sucesso!', 'success', () => {
                    // Redireciona para a tela de login após 1 segundo
                    setTimeout(() => {
                        window.location.href = 'login.html'; // Substitua 'login.html' pela URL da sua tela de login
                    }, 500);
                });
                form.reset(); // Limpa o formulário
            } else {
                showMessage('Erro ao cadastrar paciente.', 'error');
            }
        } catch (error) {
            console.error('Erro:', error);
            showMessage('Erro ao cadastrar paciente.', 'error');
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
