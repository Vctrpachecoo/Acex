using AcexxII.API.Models;
using AcexxII.API.Repositories;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace AcexxII.API.Controllers
{

    // Critério do nome da rota 
    [Route("[controller]")]
    [ApiController]
    public class CadastroPacienteController : ControllerBase
    { 
        private readonly ICadastroPacienteInterface _paciente;
        public CadastroPacienteController(ICadastroPacienteInterface paciente)
        {
            _paciente = paciente;
        }

        [HttpPost]
        public async Task<IActionResult> CreatePaciente([FromBody] UserPaciente paciente)
        {
            // Verifica se o objeto 'paciente' é nulo. Se for, retorna um erro de solicitação inválida (400 Bad Request) com uma mensagem de erro.
            if (paciente == null)
            {
                return BadRequest("Paciente é nulo");
            }

            // Chama o método assíncrono '_paciente.RegisterPaciente(paciente)' para registrar o paciente. Este método deve ser definido em algum serviço ou repositório.
            await _paciente.RegisterPaciente(paciente);

            // Retorna uma resposta de sucesso (201 Created) com a URL do novo recurso criado. O 'CreatedAtAction' gera a URL para o método 'CreatePaciente' usando o ID do paciente.
            return Ok(new { message = "Paciente criado com sucesso" });
        }

    }
}
