$(function() {
	$("#formId").submit(function(e) {
		$("#btnSubmit").attr("disabled", true);

		return true;
	});

	$("[data-toggle='tooltip']").tooltip();

	$(".cpf-mask").mask("999.999.999-99", {
		reverse: true
	});

	var contatoBehavior = function(val) {
		return val.replace(/\D/g, "").length === 11 ? "(00) 00000-0000"
			: "(00) 0000-00009";
	}, contatoOptions = {
		onKeyPress: function(val, e, field, options) {
			field.mask(contatoBehavior.apply({}, arguments), options);
		}
	};

	$("#contato").mask(contatoBehavior, contatoOptions);

	var pageSize = $("#page-size").val();
	var page = $("#page").val();
	var qtd = $("#qtd").val();
	var filtro = $("#filtroTipo").val();
	var filtroValue = $("#inputValue").val();
	var statusSelecionado = $("#statusSelecionado").val();
	var acao = $("#acao").val();

	$(".pagination-proc").pagination(
		{
			items: qtd,
			itemsOnPage: pageSize,
			prevText: "&laquo;",
			nextText: "&raquo;",
			currentPage: page,
			ellipsePageSet: false,
			hrefTextPrefix: "?page=",
			hrefTextSuffix: "&page_size=" + pageSize + "&total=" + qtd
				+ "&statusSelecionado=" + statusSelecionado
				+ "&filtroTipo=" + filtro + "&filtroValue="
				+ filtroValue
		});

	$(".paginacao-real").pagination(
		{
			items: qtd,
			itemsOnPage: pageSize,
			prevText: "&laquo;",
			nextText: "&raquo;",
			currentPage: page,
			ellipsePageSet: false,
			hrefTextPrefix: "?acao=" + acao + "&page=",
			hrefTextSuffix: "&page_size=" + pageSize + "&total=" + qtd
				+ "&pagination=true"
		});

	$(".paginacao-real-antigo").pagination(
		{
			items: qtd,
			itemsOnPage: pageSize,
			prevText: "&laquo;",
			nextText: "&raquo;",
			currentPage: page,
			ellipsePageSet: false,
			hrefTextPrefix: "?page=",
			hrefTextSuffix: "&page_size=" + pageSize + "&total=" + qtd
				+ "&pagination=true"
		});

	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip({
			placement: 'bottom'
		});
	});

	$(document).keypress(function(e) {
		if (e.which == 13)
			$('.enterBtn').click();
	});

});

// Popup para campo vazio
function fecharPrazo() {
	var filtroValue = $("#inputValue").val();
	if (!filtroValue) {
		this.event.preventDefault();
		$("#inputValue").popover("enable");
		$("#inputValue").popover("show");
		setTimeout(function() {
			$("#inputValue").popover("hide");
			$("#inputValue").popover("disable");
		}, 3000);
	} else {
		if (!confirm("Suspender os processos administrativos?")) {
			this.event.preventDefault();
		}
	}
}

function pergunta() {
	// retorna true se confirmado, ou false se cancelado
	return confirm('Tem certeza que deseja continuar?');
}

function changeInput() {
	var e = document.getElementById("filtroTipo");
	if (e.value.localeCompare("DATA_ABERTURA") == 0) {
		document.getElementById("inputValue").type = "date";
	} else {
		document.getElementById("inputValue").type = "text";
	}
}

function SomenteNumero(campo) {
	var tecla = (window.event) ? event.keyCode : e.which;
	if ((tecla > 47 && tecla < 58))
		return true;
	else {
		if (tecla == 8 || tecla == 0)
			return true;
		else
			return false;
	}
}

// function isNumber(event){
// var tecla = (window.event) ? event.keyCode : e.which;
// var number = /^[0-9]+$/;
// if(tecla.value.match(number)){
// return true
// }
// return false
// }

function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}

// limita entrada campo input a apenas numeros
function setInputFilter(textbox, inputFilter, errMsg) {
	["input", "keydown", "keyup", "mousedown", "mouseup", "select",
		"contextmenu", "drop", "focusout"].forEach(function(event) {
			textbox.addEventListener(event,
				function(e) {
					if (inputFilter(this.value)) {
						// Accepted value
						if (["keydown", "mousedown", "focusout"]
							.indexOf(e.type) >= 0) {
							this.classList.remove("input-error");
							this.setCustomValidity("");
						}
						this.oldValue = this.value;
						this.oldSelectionStart = this.selectionStart;
						this.oldSelectionEnd = this.selectionEnd;
					} else if (this.hasOwnProperty("oldValue")) {
						// Rejected value - restore the previous one
						this.classList.add("input-error");
						this.reportValidity();
						this.value = this.oldValue;
						this.setSelectionRange(this.oldSelectionStart,
							this.oldSelectionEnd);
					} else {
						// Rejected value - nothing to restore
						this.value = "";
					}
				});
		});
}

function SomenteNumero(campo) {
	var tecla = (window.event) ? event.keyCode : e.which;
	if ((tecla > 47 && tecla < 58))
		return true;
	else {
		if (tecla == 8 || tecla == 0)
			return true;
		else
			return false;
	}
}

function removeNonDigits() {
	var input = document.getElementById("inputBusca");

	var value = input.value;
	var digits = "";

	for (var i = 0; i < value.length; i++) {
		var charCode = value.charCodeAt(i);

		if (charCode > 47 && charCode < 58) {
			digits += value.charAt(i);
		}
	}

	input.value = digits;
}

function onlyNumberKey(evt) {

	// Only ASCII character in that range allowed
	var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
		return false;
	return true;
}

function mascaraCartao() {
	$(document).ready(function() {
		var numeroCartao = $("#inputNumCartao").text();
		numeroCartao = numeroCartao.padStart(10, '0');
		$("#inputNumCartao").html(numeroCartao).mask('9.999.999.999');
	});
}


function validaSenha(input) {
	if (input.value != document.getElementById("txtSenha").value) {
		input.setCustomValidity("Repita a senha corretamente");
	} else {
		input.setCustomValidity('');
	}
}

function somente_inteiro(campo) {
	var digits = "0123456789";
	var campo_temp;
	for (var i = 0; i < campo.value.length; i++) {
		campo_temp = campo.value.substring(i, i + 1);
		if (digits.indexOf(campo_temp) == -1) {
			campo.value = campo.value.substring(0, i);
		}
	}
}

function somente_float(campo) {
	var digits = "0123456789,";
	var campo_temp;
	for (var i = 0; i < campo.value.length; i++) {
		campo_temp = campo.value.substring(i, i + 1);
		if (digits.indexOf(campo_temp) == -1) {
			campo.value = campo.value.substring(0, i);
		}
	}
}

function mascaraData(data) {
	if (mascaraInteiro(data) == false) {
		event.returnValue = false;
	}
	return formataCampo(data, "99/99/9999", event);
}

function mascaraDataHora(data) {
	if (mascaraInteiro(data) == false) {
		event.returnValue = false;
	}
	return formataCampo(data, "99/99/9999 00:00", event);
}

function mascaraInteiro() {
	if (event.keyCode < 48 || event.keyCode > 57) {
		event.returnValue = false;
		return false;
	}
	return true;
}

function formataCampo(campo, Mascara, evento) {
	var boleanoMascara;

	var Digitato = evento.keyCode;
	exp = /\-|\.|\/|\(|\)| /g
	campoSoNumeros = campo.value.toString().replace(exp, "");

	var posicaoCampo = 0;
	var NovoValorCampo = "";
	var TamanhoMascara = campoSoNumeros.length;

	if (Digitato != 8) { // backspace
		for (i = 0; i <= TamanhoMascara; i++) {
			boleanoMascara = ((Mascara.charAt(i) == "-")
				|| (Mascara.charAt(i) == ".") || (Mascara.charAt(i) == "/"))
			boleanoMascara = boleanoMascara
				|| ((Mascara.charAt(i) == "(")
					|| (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " "))
			if (boleanoMascara) {
				NovoValorCampo += Mascara.charAt(i);
				TamanhoMascara++;
			} else {
				NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
				posicaoCampo++;
			}
		}

		campo.value = NovoValorCampo;
		return true;
	} else {
		return true;
	}
}

function validaData(data) {
	exp = /^(((0[1-9]|[12][0-9]|3[01])([-.\/])(0[13578]|10|12)([-.\/])(\d{4}))|(([0][1-9]|[12][0-9]|30)([-.\/])(0[469]|11)([-.\/])(\d{4}))|((0[1-9]|1[0-9]|2[0-8])([-.\/])(02)([-.\/])(\d{4}))|((29)(\.|-|\/)(02)([-.\/])([02468][048]00))|((29)([-.\/])(02)([-.\/])([13579][26]00))|((29)([-.\/])(02)([-.\/])([0-9][0-9][0][48]))|((29)([-.\/])(02)([-.\/])([0-9][0-9][2468][048]))|((29)([-.\/])(02)([-.\/])([0-9][0-9][13579][26])))$/;
	if (!exp.test(data.value)) {
		data.value = "";
		alert("Data Invalida!");
		data.select();
	}
}

function onBtnFiltrar() {
	var form = document.getElementById("form");

	form.page.value = 1;
	form.page_size = 20;

	form.submit();
}

// Filtro para NÃºmeros inteiros positivos (CPF, Numero Cartao, etc):
function filtroCPF() {
	setInputFilter(document.getElementById("cpf"), function(valor) {
		return /^\d*$/.test(valor);
	}, "Inserir apenas numeros");
}

function tootip_icon() {
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip({
			placement: 'bottom'
		});
	});
}

function mascaraCPF() {
	$("#cpf").mask("999.999.999-99", {
		reverse: true
	});
}

function mascaraCEP() {
	$("#cep").mask("99.999-999", {
		reverse: true
	});
}

(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
