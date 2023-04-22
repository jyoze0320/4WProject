function NumFormat(t) 
{
	s = t.value;
	s = s.replace(/\D/g, '');
	l = s.length - 3;
	while (l > 0) {
		s = s.substr(0, l) + ',' + s.substr(l);
		l -= 3;
	}
	t.value = s;
	return t;
}


function go_save() 
{
	var theForm = document.newfrm;
	
	if (theForm.pd_kind.value == '') {
		alert('상품분류를 선택하세요.');
		theForm.kind.focus();
	} else if (theForm.pd_name.value == '') {
		alert('상품명을 입력하세요.');
		theForm.pd_name.focus();
	} else if (theForm.cost_price.value == '') {
		alert('원가를 입력하세요.');
		theForm.cost_price.focus();
	} else if (theForm.sell_price.value == '') {
		alert('판매가를 입력하세요.');
		theForm.sell_price.focus();
	} else if (theForm.pd_content.value == '') {
		alert('상품상세를 입력하세요.');
		theForm.pd_content.focus();
	} else if (theForm.pd_image.value == '') {
		alert('제품 이미지를 입력하세요.');
		theForm.pd_image.focus();
	}
	else {
	
		theForm.action = "4w.do?command=admin_product_write";
		theForm.submit();
}	
}

function removeComma(input) 
{
	return input.value.replace(/,/gi, "");
}


function go_mov()
{
	var theForm = document.frm;
	theForm.action = "4w.do?command=admin_product_list";
	theForm.submit();
}


function go_search() {
	var theForm = document.frm;
	theForm.action =  "4w.do?command=admin_product_list";
	theForm.submit();
}

function go_total() {
	var theForm = document.frm;
	theForm.key.value = "";
	theForm.action =  "4w.do?command=admin_product_list";
	theForm.submit();
}


function go_wrt() {
	var theForm = document.frm;
	theForm.action = "4w.do?command=admin_product_write_form";
	theForm.submit();
}




