using AcexxII.API.App.Interfaces;
using AcexxII.API.Models;
using AcexxII.API.Repositories;
using AcexxII.API.Repository.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace AcexxII.API.App.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class CadastroPsicologoController : ControllerBase
    {

        // Injeção de dependência do método 
        private readonly ICadastroPsicologoInterface _pscicologo;

        // Declarando o método dentro do controller 
        public CadastroPsicologoController(ICadastroPsicologoInterface pscicologo)
        {
            _pscicologo = pscicologo;
        }



        [HttpPost]
        public async Task<IActionResult> CreatePscicologo([FromBody] UserPsicologo pscicologo)
        {
            // Verifica se o objeto 'pscicologo' é nulo. Se for, retorna um erro de solicitação inválida (400 Bad Request) com uma mensagem de erro.
            ///if (pscicologo == null)
            //{
                // BadRequest("Paciente é nulo");
            //}

            // Chama o método assíncrono '_paciente.RegisterPaciente(paciente)' para registrar o paciente. Este método deve ser definido em algum serviço ou repositório.
            await _pscicologo.RegisterPscicologo(pscicologo);

            // Retorna uma resposta de sucesso (201 Created) com a URL do novo recurso criado. O 'CreatedAtAction' gera a URL para o método 'CreatePaciente' usando o ID do paciente.
            return Ok(new { message = "Paciente criado com sucesso" });
        }




    }
}
