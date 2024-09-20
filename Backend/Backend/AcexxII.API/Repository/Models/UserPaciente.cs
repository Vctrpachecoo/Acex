using System.ComponentModel.DataAnnotations;

namespace AcexxII.API.Models
{

    // Model tabela Paciente 
    public class UserPaciente
    {

        [Key]
        public long Id { get; set; }

        public string NomeCompleto { get; set; } = string.Empty;

        public string Email { get; set; } = string.Empty;

        public string Senha { get; set; } = string.Empty;

        public string ConfirmaSenha { get; set; } = string.Empty;


    }
}
