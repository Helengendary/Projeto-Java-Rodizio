# PasswordEncoderService.java

## String encode(String password)

Criptografa uma senha

### Parâmetros:

`password : String`

A senha que será criptografada.

### Retorno: String

A senha criptografada, usando algum algoritmo de criptografia qualquer.

## boolean matches(String password, String encodedPassword)

Compara uma senha sem criptografia com uma criptografada.

### Parâmetros:

`password : String`

Uma senha sem criptografia.

`encodedPassword : String`

Uma senha criptografada com o método encode da mesma classe.

### Retorno: boolean

Deve ser `true` se as senhas forem iguais, `false` se diferentes.

> ### Observações:
> Esta função existe pois certos algoritmos de criptografia geram resultados diferentes com o mesmo input, não permitindo se criptografar a senha novamente com o método encode e comparar os resultados. Caso a implementação não utilize destes algoritmos, use o encode e compare nesta função, retornando o resultado.

---

# JwtService.java

## String get(T token)

Recebe um objeto com dados genérico e criptografa seus dados em um Json Web Token

### Parâmetros:

`token : T`

O objeto que será transformado em jwt. O tipo dele é definido na implementação da classe.

### Retorno: String

Uma String em formato jwt com os dados de `token`.

## T validate(String jwt)

Transforma uma String em formato jwt em um objeto tipo T, definido na implementação da classe.

### Parâmetros:

`jwt : String`

Uma String em formato jwt com os dados do objeto.

### Retorno: T

O objeto T com os dados contidos em `jwt`. Deve ser `null` se os dados não puderem ser recuperados.

---

# PasswordValidationService.java

## PasswordValidateCode validate(String password)

Valida se a senha passa pelos critérios de segurança especificados.

### Parâmetros:

`password : String`

A senha que será validada.

### Retorno: PasswordValidateCode

A função retornará uma constante do enum PasswordValidateCode, que será um dos seguintes valores:

| Valor | Descrição |
| --- | --- |
| OK | A senha passou por todos os critérios de segurança |
| LENGTH | A senha não é longa o bastante |
| UPPER_CASE | A senha não possui letras maiúsculas o bastante |
| LOWER_CASE | A senha não possui letras minúsculas o bastante |
| NUMBER | A senha não possui números o bastante |

---

# AuthService.java

## User login(String edv, String password)

Tenta realizar o login de um usuário através os dados passados por parâmetro.

### Parâmetros:

`login : String`

A identificação (edv ou email) do usuário que tenta logar.

`password : String`

A senha fornecida pelo usuário.

### Retorno: User

Um objeto do tipo User, recuperado do banco de dados.  
Retorna `null` se não encontrar no banco.

## String getAuth(User user)

Cria uma String em formato jwt através dos dados do usuário.

### Parâmetros:

`user : User`

Os dados do usuário, preferencialmente adquiridos através do método `login`.

### Retorno: String

Uma String em formato jwt que será usada para autenticar o usuário em requisições posteriores.

---

# UserValidationServices.java

## boolean validationAnswer(User respondent, Question question)

verifica se o usuário `respondent` pode responder à pergunta `question`.

### Parâmetros:

`respondent : User`

O usuário que tenta responder à pergunta.

`question : Question`

A pergunta que será respondida.

### Retorno : boolean

`true` se `user` tiver permissão, `false` caso contrário.

## boolean isQuestioner(User user, Question question)

verifica se o usuário `user` é quem criou a pergunta `question`.

### Parâmetros:

`user : User`

O usuário.

`question : Question`

A pergunta.

### Retorno : boolean

`true` se `user` tiver criado a pergunta, `false` caso contrário.

## boolean isAdm(User user, Spaces space)

verifica se o usuário `user` é admin do Space `space`.

### Parâmetros:

`user : User`

O usuário.

`space : Space`

O Space.

### Retorno : boolean

`true` se `user` for admin de `space`, `false` caso contrário.
