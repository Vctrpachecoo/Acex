using AcexxII.API.Repository.Models;

namespace AcexxII.API.App.Interfaces
{
    public interface ILoginUserInterface
    {

        Task<string> LoginAsync(UsersLoginModel loginModel);
    }
}
