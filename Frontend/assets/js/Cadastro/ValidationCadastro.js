// JavaScript para habilitar/desabilitar campos de CRP e Especialidade e escurecer os campos desabilitados
        const pacienteRadio = document.getElementById('tipo_paciente');
        const psicologoRadio = document.getElementById('tipo_psicologo');
        const crpField = document.getElementById('cadastro_1_crp');
        const especialidadeField = document.getElementById('cadastro_1_especialidade');

        // Função para limpar os campos e desabilitar
        function toggleFields() {
            if (pacienteRadio.checked) {
                crpField.value = ''; // Limpa o campo CRP
                especialidadeField.value = ''; // Limpa o campo Especialidade
                crpField.disabled = true;
                especialidadeField.disabled = true;
                crpField.style.backgroundColor = '#e0e0e0'; // Escurecer o campo desabilitado
                especialidadeField.style.backgroundColor = '#e0e0e0'; // Escurecer o campo desabilitado
            } else {
                crpField.disabled = false;
                especialidadeField.disabled = false;
                crpField.style.backgroundColor = ''; // Restaurar a cor original
                especialidadeField.style.backgroundColor = ''; // Restaurar a cor original
            }
        }

        // Marcar a opção "Paciente" por padrão e desabilitar os campos ao carregar a página
        window.onload = function() {
            toggleFields();
        };

        // Adiciona os eventos de mudança para os radios
        pacienteRadio.addEventListener('change', toggleFields);
        psicologoRadio.addEventListener('change', toggleFields);

       
   
