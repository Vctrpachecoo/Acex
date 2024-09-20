using System.ComponentModel.DataAnnotations;

namespace AcexxII.API.Repository.Models
{

    // Model tabela pscicologo 
    public class UserPsicologo
    {
        [Key]
        public long Id { get; set; }

        public string Nome { get; set; } = string.Empty;

        public string Email { get; set; } = string.Empty;

        public string CRP { get; set; } = string.Empty;

        public string Especialidade { get; set; } = string.Empty;

        public string Senha { get; set; } = string.Empty;

        public string ConfirmaSenha { get; set; } = string.Empty;

    }
}
