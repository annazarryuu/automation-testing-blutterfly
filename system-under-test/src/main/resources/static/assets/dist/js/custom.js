var MSTD0105AINF = "MSTD0105AINF : Pilih salah satu record untuk dihapus";
var MSTD0031AERR = "MSTD0031AERR : :0 tidak boleh kosong";
var MSTD0106AINF = "MSTD0106AINF : Pilih salah satu record untuk dimodifikasi";
var MSTD0004ACFM = "MSTD0004ACFM : Are you sure you want to deactivate the :0 data ?";
var MSTD1004ACFM = "MSTD0004ACFM : Are you sure you want to deactivate the :0 data ?";
var MSTD0001ACFM = "MSTD0001ACFM : Apakah anda yakin untuk menghapus data ini ?";
var MSTD0043AERR = "MSTD0043AERR : Kesalahan :0";
var MSTD0000AERR = ":0";
var MSTD0000AINF = ":0";
var MSTD1073AERR = "MSTD1073AERR : :0 tidak bisa diunduh karena SPK belum selesai.";
var MSTD1074AERR = "MSTD1074AERR : :0 tidak bisa diubah karena sudah diproses.";
var MSTD1075AERR = "MSTD1075AERR : :0 tidak bisa diubah karena belum dilakukan pengujian.";
var MSTD1076ACFM = "MSTD1076ACFM : Apakah Anda yakin untuk mengirimkan :0 ini?";
var MSTD1077ACFM = "MSTD1077ACFM : Apakah Anda yakin untuk :0 :1 ini?";
var MSTD1078AERR = "MSTD1078AERR : Anda sudah pernah mengubah SPK ini. SPK hanya bisa diubah 1 (satu) kali.";
var MSTD1079AERR = "MSTD1079AERR : Sertifikat tidak tersedia."
var	MSTD1082AERR = "MSTD1082AERR : Proses pengujian belum selesai dilaksanakan."
var MSTD1083ACFM = "MSTD1083ACFM : Apakan Anda yakin bahwa pengujian telah selesai dilaksanakan?"

var SEARCH = "Menampilkan Data";
var ADD = "Menambah Data";
var EDIT = "Mengubah Data";
var DELETE = "Menghapus Data";
var RESCHEDULE = "Menentukan Tanggal Uji Fungsi";
var RESULT = "Memberikan Hasil Uji Fungsi";
var APPROVE = "Menyetujui Tanggal Uji Fungsi";

function showMessage(code, text) {
	var msg = code.slice(-3);
	var stMessageStyle = "info";
	if ("ERR" == msg) {
		stMessageStyle = "error";
	}

	toastr.options = {
		"closeButton" : true,
		"debug" : false,
		"positionClass" : "toast-top-right",
		"onclick" : null,
		"showDuration" : "300",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
	}

	var $toast = toastr[stMessageStyle]("", text);
}

function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}


function alertMessagesModal(header,messages,btnAction){
  $("#modal-header").html(header);
  $("#modal-message").html(messages);
  
  $("#alert-messages").modal({
		backdrop: 'static',
		keyboard: true,
		show: true
  });
  
  $('#btn-modal').attr('onclick',btnAction);
}

$(document).on('show.bs.modal', '.modal', function (event) {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function() {
        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
});

$(document).on('hidden.bs.modal', '.modal', function () {
    $('.modal:visible').length && $(document.body).addClass('modal-open');
});

function alertMessageDisponse(){
  $("#alert-messages").modal("toggle");
}

function pagination(data){
  var format ="";
  $(".pagination").empty();
  if(data.totalPage > 0){
    if(data.currentPage != 1){
      format += '<li><a href="javascript:void(0);" onclick="doSearch('+ (data.currentPage - 1) +')">'+'&laquo;</a></li>';
    }else{
      format += '<li class="disabled"><a class="disabled" href="javascript:void(0);">'+'&laquo;</a></li>';
    }

    if(data.totalPage > 10){
      var c = Math.ceil(data.currentPage/10);
      if(data.currentPage > 10){
          format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch(1)">1</a>'+'</li>';
          format += '<li><a href="javascript:void(0)">...</a></li>';
      }
      
      for(var i = 1; i <= 10; i++){
        var num = i + ((c-1) * 10);
        if(num <= data.totalPage){
          if(num == data.currentPage){
            format += '<li class="active">'+ '<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+ '</li>';
          }else{
            format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+'</li>';
          }
        }
      }

      if(data.currentPage < data.totalPage){
    	  format += '<li><a href="javascript:void(0)">...</a></li>';
          format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch('+data.totalPage+')">'+data.totalPage+'</a>'+'</li>';
      }
   
    }else{
      
      for(var i = 0; i < data.totalPage; i++){
        var num = i + 1;
        if((i + 1) == data.currentPage){
          format += '<li class="active">'+ '<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+ '</li>';
        }else{
          format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+'</li>';
        }
      }
    }

    if(data.currentPage < data.totalPage){
      format +='<li><a href="javascript:void(0);" onclick="doSearch('+ (data.currentPage + 1) +')">&raquo;</a></li>';
    }else{
      format +='<li class="disabled"><a class="disabled" href="javascript:void(0);">&raquo;</a></li>';
    }
  }else{
    format += '<li class="disabled"><a class="waves-effect disabled" href="javascript:void(0);">&laquo;</a></li>';
    format += '<li class="disabled"><a class="waves-effect" href="javascript:void(0)">1</a></li>';
    format += '<li class="disabled"><a class="waves-effect disabled" href="javascript:void(0);">&raquo;</a></li>';
  }

  $(".pagination").append(format);
}

function pagination3(data, sizeRow){
	  var format ="";
	  $(".pagination").empty();
	  if(data.totalPage > 0){
	    
		  // first button
		if(data.currentPage != 1){
	      format += '<li><a href="javascript:void(0);" onclick="doSearch('+ (1) +','+sizeRow+')">'+'First </a></li>';
	    }else{
	      format += '<li class="disabled"><a class="disabled" href="javascript:void(0);">'+'First</a></li>';
	    }
		// end first button 
		
	    if(data.currentPage != 1){
	      format += '<li><a href="javascript:void(0);" onclick="doSearch('+ (data.currentPage - 1) +','+sizeRow+')">'+'&laquo;</a></li>';
	    }else{
	      format += '<li class="disabled"><a class="disabled" href="javascript:void(0);">'+'&laquo;</a></li>';
	    }

//	    if(data.totalPage > 10){
//	      var c = Math.ceil(data.currentPage/10);
//	      if(data.currentPage > 10){
//	          format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch(1)">1</a>'+'</li>';
//	          format += '<li><a href="javascript:void(0)">...</a></li>';
//	      }
//	      
//	      for(var i = 1; i <= 10; i++){
//	        var num = i + ((c-1) * 10);
//	        if(num <= data.totalPage){
//	          if(num == data.currentPage){
//	            format += '<li class="active">'+ '<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+ '</li>';
//	          }else{
//	            format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+'</li>';
//	          }
//	        }
//	      }
//
//	      if(data.currentPage < data.totalPage){
//	    	  format += '<li><a href="javascript:void(0)">...</a></li>';
//	          format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch('+data.totalPage+')">'+data.totalPage+'</a>'+'</li>';
//	      }
//	   
//	    }else{
//	      
//	      for(var i = 0; i < data.totalPage; i++){
//	        var num = i + 1;
//	        if((i + 1) == data.currentPage){
//	          format += '<li class="active">'+ '<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+ '</li>';
//	        }else{
//	          format += '<li>'+'<a href="javascript:void(0)" onclick="doSearch('+num+')">'+num+'</a>'+'</li>';
//	        }
//	      }
//	    }

	    if(data.currentPage < data.totalPage){
	      format +='<li><a href="javascript:void(0);" onclick="doSearch('+ (data.currentPage + 1) +','+sizeRow+')">&raquo;</a></li>';
	    }else{
	      format +='<li class="disabled"><a class="disabled" href="javascript:void(0);">&raquo;</a></li>';
	    }
	    // last button
	    if(data.currentPage < data.totalPage){
		      format +='<li><a href="javascript:void(0);" onclick="doSearch('+ (data.totalPage) +','+sizeRow+')">Last</a></li>';
		    }else{
		      format +='<li class="disabled"><a class="disabled" href="javascript:void(0);">Last</a></li>';
		    }
	    // end last button
	  }else{
	    format += '<li class="disabled"><a class="waves-effect disabled" href="javascript:void(0);">&laquo;</a></li>';
	    format += '<li class="disabled"><a class="waves-effect" href="javascript:void(0)">1</a></li>';
	    format += '<li class="disabled"><a class="waves-effect disabled" href="javascript:void(0);">&raquo;</a></li>';
	  }

	  $(".pagination").append(format);
	}

function pagination2(data){
	  var format ="";
	  $(".pagination").empty();
	  if(data.totalPage > 0){
	    if(data.currentPage != 1){
	      format += '<li><a href="javascript:void(0);" onclick="doFilter('+ (data.currentPage - 1) +')">'+'&laquo;</a></li>';
	    }else{
	      format += '<li class="disabled"><a class="disabled" href="javascript:void(0);">'+'&laquo;</a></li>';
	    }

	    if(data.totalPage > 10){
	      var c = Math.ceil(data.currentPage/10);
	      if(data.currentPage > 10){
	          format += '<li>'+'<a href="javascript:void(0)" onclick="doFilter(1)">1</a>'+'</li>';
	          format += '<li><a href="javascript:void(0)">...</a></li>';
	      }
	      
	      for(var i = 1; i <= 10; i++){
	        var num = i + ((c-1) * 10);
	        if(num <= data.totalPage){
	          if(num == data.currentPage){
	            format += '<li class="active">'+ '<a href="javascript:void(0)" onclick="doFilter('+num+')">'+num+'</a>'+ '</li>';
	          }else{
	            format += '<li>'+'<a href="javascript:void(0)" onclick="doFilter('+num+')">'+num+'</a>'+'</li>';
	          }
	        }
	      }

	      if(data.currentPage < data.totalPage){
	    	  format += '<li><a href="javascript:void(0)">...</a></li>';
	          format += '<li>'+'<a href="javascript:void(0)" onclick="doFilter('+data.totalPage+')">'+data.totalPage+'</a>'+'</li>';
	      }
	   
	    }else{
	      
	      for(var i = 0; i < data.totalPage; i++){
	        var num = i + 1;
	        if((i + 1) == data.currentPage){
	          format += '<li class="active">'+ '<a href="javascript:void(0)" onclick="doFilter('+num+')">'+num+'</a>'+ '</li>';
	        }else{
	          format += '<li>'+'<a href="javascript:void(0)" onclick="doFilter('+num+')">'+num+'</a>'+'</li>';
	        }
	      }
	    }

	    if(data.currentPage < data.totalPage){
	      format +='<li><a href="javascript:void(0);" onclick="doFilter('+ (data.currentPage + 1) +')">&raquo;</a></li>';
	    }else{
	      format +='<li class="disabled"><a class="disabled" href="javascript:void(0);">&raquo;</a></li>';
	    }
	  }else{
	    format += '<li class="disabled"><a class="waves-effect disabled" href="javascript:void(0);">&laquo;</a></li>';
	    format += '<li class="disabled"><a class="waves-effect" href="javascript:void(0)">1</a></li>';
	    format += '<li class="disabled"><a class="waves-effect disabled" href="javascript:void(0);">&raquo;</a></li>';
	  }

	  $(".pagination").append(format);
}

function current_page(){
  var $li = $(".pagination").find('li.active');
  var curr = $li.find("a").text();
  if(curr != null || curr != "undefined"){
    return curr;
  }else{
    return "1";
  }
}

function getMessage(msg, rep){
  return msg.replace(":0",rep);
}

function getMessage(msg, rep, rep1){
  return msg.replace(":0",rep).replace(":1",rep1);
}

function eMandatory(dict){
  var msg="";
  var count = 0;
  if(dict != null && dict.length > 0){
      for (var i = 0; i < dict.length; i++) {
          var tmp = $(dict[i]["method"]).val();
          if(tmp == null || tmp == undefined || tmp == ""){
            if(i == dict.length - 1){
              msg += " and "+dict[i]["label"];
            }else{
              msg += ", "+dict[i]["label"];
            }
            count++;
          }
      }

      if(count < 2){
        msg = msg.replace("and","");
      }

      return msg.substr(1,msg.length);
  }

  return "";
}

function combobox(method, id, tabindex, field, select, url){
  $.post(url,{}).done(function(response) {
      var data = response.data;
      $(method).empty();
      var tag = '<select class="form-control show-tick" data-live-search="true" id="'+id+'" name="'+id+'" tabindex="'+tabindex+'">';
      tag +="<option value=''>"+select+"</option>";
      if(data != null && data.length > 0){
        for (var i = 0; i < data.length; i++) {
          tag +='<option value="'+data[i][field[0]]+'">'+data[i][field[0]]+' - '+data[i][field[1]]+'</option>';
        }
      }
      tag +='</select>';
      $(method).append(tag);
      $(".show-tick").selectpicker();
  });
}

function _cmbModel1(method, field, select, data){
  $(method).empty();
  $(method).append('<option value="">'+select+'</option>');
  if(data != null && data.length > 0){
	  for (var i = 0; i < data.length; i++) {
		  $(method).append('<option value="'+data[i][field[0]]+'">'+data[i][field[0]]+' - '+data[i][field[1]]+'</option>');
	  }
  }
}

function _cmbModel2(method, field, select, data){
  $(method).empty();
  $(method).append('<option value="">'+select+'</option>');
  if(data != null && data.length > 0){
	  for (var i = 0; i < data.length; i++) {
		  $(method).append('<option value="'+data[i][field[0]]+'">'+data[i][field[0]]+'</option>');
	  }
  }
}

function _cmbModel3(method, field, select, data){
  $(method).empty();
  $(method).append('<option value="">'+select+'</option>');
  if(data != null && data.length > 0){
	  for (var i = 0; i < data.length; i++) {
		  $(method).append('<option value="'+data[i][field[0]]+'">'+data[i][field[1]]+'</option>');
	  }
  }
}

function _cmbModel4(method, field, select, data){
  $(method).empty();
  $(method).append('<option value="">'+select+'</option>');
  if(data != null && data.length > 0){
	  for (var i = 0; i < data.length; i++) {
		 $(method).append('<option value="'+data[i][field[0]]+'">'+data[i][field[0]]+' - '+data[i][field[1]]+'</option>');
	  }
  }
}

function toRp(angka){
    var rev     = parseInt(angka, 10).toString().split('').reverse().join('');
    var rev2    = '';
    for(var i = 0; i < rev.length; i++){
        rev2  += rev[i];
        if((i + 1) % 3 === 0 && i !== (rev.length - 1)){
            rev2 += '.';
        }
    }
    return 'Rp. ' + rev2.split('').reverse().join('') + ',00';
}

function toString(s){
    try {
		if(s != null && s != "" && s != "undefined"){
			return s;
		}else{
			return "";
		}
	} catch (e) {
		return "";
	}
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
