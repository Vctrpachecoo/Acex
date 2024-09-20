using AcexxII.API.Models;
using AcexxII.API.Repository.Models;

namespace AcexxII.API.App.Interfaces
{
    public interface ICadastroPsicologoInterface
    {


        Task RegisterPscicologo(UserPsicologo paciente);


    }
}
