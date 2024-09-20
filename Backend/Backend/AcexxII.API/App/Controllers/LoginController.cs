using AcexxII.API.App.Interfaces;
using AcexxII.API.Repository.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace AcexxII.API.App.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class LoginController : ControllerBase
    {


        private readonly ILoginUserInterface _loginUser;

        public LoginController(ILoginUserInterface loginUser)
        {
            _loginUser = loginUser;
        }

        [HttpPost]
        public async Task<IActionResult> Login([FromBody] UsersLoginModel loginModel)
        {
            if (loginModel == null || string.IsNullOrEmpty(loginModel.Email) || string.IsNullOrEmpty(loginModel.Senha))
            {
                return BadRequest(new { message = "Email e senha são obrigatórios." }); // Retorne um objeto JSON
            }

            try
            {
                var result = await _loginUser.LoginAsync(loginModel);
                return Ok(new { message = result }); // Retorne um objeto JSON com a mensagem
            }
            catch (UnauthorizedAccessException ex)
            {
                return Unauthorized(new { message = ex.Message }); // Retorne um objeto JSON
            }
            catch (Exception ex)
            {
                return StatusCode(500, new { message = "Erro interno do servidor." }); // Retorne um objeto JSON
            }
        }
    }
}

