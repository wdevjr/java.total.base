/**
 * Validar campos obrigatorios
 * 
 * @author Professor Jose de Assis
 */

function validar() {
	let nome = formEdita.nome.value
	let precoCusto = formEdita.fone.value
	if (nome === "") {
		alert('Preencha o campo Nome')
		formEdita.nome.focus()
		return false
	} else if (precoCusto === "") {
		alert('Preencha o campo Fone')
		formEdita.precoCusto.focus()
		return false
	} else {
		document.forms["formEdita"].submit()
	}
}