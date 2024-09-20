namespace AcexxII.API.App.Services;

using AcexxII.API.Repository.Models;
using FluentValidation;

    public class PsicologoValidator : AbstractValidator<UserPsicologo>
{

        public PsicologoValidator()
        {
            RuleFor(user => user.Nome)
                .NotEmpty().WithMessage("O nome completo é obrigatório.")
                .Length(2, 100).WithMessage("O nome completo deve ter entre 2 e 100 caracteres.");

            RuleFor(user => user.Email)
                .NotEmpty().WithMessage("O e-mail é obrigatório.")
                .EmailAddress().WithMessage("O e-mail deve estar em um formato válido.");

            RuleFor(user => user.CRP) 
                .NotEmpty().WithMessage("O Campo de CRP não pode ser vazio!")
                .Length(2, 5).WithMessage("CRP inválido");

            RuleFor(user => user.Especialidade)
                .NotEmpty().WithMessage("Insira a sua especialidade!");

            RuleFor(user => user.Senha)
                .NotEmpty().WithMessage("A senha é obrigatória.")
                .MinimumLength(6).WithMessage("A senha deve ter pelo menos 6 caracteres.")
                .Matches("[A-Z]").WithMessage("A senha deve conter pelo menos uma letra maiúscula.")
                .Matches("[!@#$%^&*(),.?\":{}|<>]").WithMessage("A senha deve conter pelo menos um caractere especial.");

            RuleFor(user => user.ConfirmaSenha)
                .Equal(user => user.Senha).WithMessage("A confirmação da senha deve corresponder à senha.");

    }


}

