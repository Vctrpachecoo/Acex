using AcexxII.API.Context;
using AcexxII.API.Repositories;
using AcexxII.API.Services;
using Microsoft.EntityFrameworkCore;
using FluentValidation.AspNetCore;
using AcexxII.API.App.Interfaces;
using AcexxII.API.App.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();


// Conexão com o banco de dados 
builder.Services.AddDbContext<ApplicationDbContext>(options =>
options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

// Services 
builder.Services.AddScoped<ICadastroPacienteInterface, CadastroPacienteServices>();
builder.Services.AddScoped<ICadastroPsicologoInterface, CadastroPsicologoService>(); 
builder.Services.AddScoped<ILoginUserInterface, LoginUserService>();

// Validator 
builder.Services.AddControllers()
    .AddFluentValidation(fv => fv.RegisterValidatorsFromAssemblyContaining<PacienteValidator>());
builder.Services.AddControllers()
    .AddFluentValidation(fv => fv.RegisterValidatorsFromAssemblyContaining<PsicologoValidator>());


// Configurando Cors para receber requisição de qualquer porta 
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAllOrigins",
        policyBuilder =>
        {
            policyBuilder.AllowAnyOrigin()
                         .AllowAnyMethod()
                         .AllowAnyHeader();
        });
});

var app = builder.Build();

// Usa a política de CORS configurada
app.UseCors("AllowAllOrigins");  

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();


app.MapControllers();

app.Run();

// Testando isso aqui.. 
