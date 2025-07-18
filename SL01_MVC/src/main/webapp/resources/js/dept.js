console.log("Dept Module........");

// 자체 호출 함수
const deptService = (function(){

	// [1] 새로운 부서 추가
	// http://localhost/scott/dept/new + POST 요청
	function add(dept, callback, error){
		console.log("deptService.add()........");
		
		// 부서 추가하는 ajax
		$.ajax({
			type: 'POST',
			url: '/scott/dept/new',
			data: JSON.stringify(dept),
			contentType: "application/json; charset=utf-8",
			cache: false,
			beforeSend: function(xhr){
				console.log("add.beforeSend ...............");
			},
			success: function(result, status, xhr){          
	          if( callback ){
	              callback( result );
	          } // if
	        },
	        error: function (xhr, status, er){           
				if( error ){
                	error( er );
        		} // if
        	}
        })
        .fail(function() {
			alert( "ajax 부서 추가 실패!!!" );
		});
	} // add
	
	// [2] 부서 삭제
	// [2-1] 부서 삭제
	// http://localhost/scott/dept/50 + DELETE 요청 -> REST PULL ? 방식
	function remove(deptno, callback, error){
		console.log("deptService.remove()........");
		
		// 부서 삭제하는 ajax
		$.ajax({
			type: 'DELETE',
			url: `/scott/dept/${deptno}`,
			cache: false,
			beforeSend: function(xhr){
				console.log("remove.beforeSend ...............");
			},
			success: function(result, status, xhr){          
	          if( callback ){
	              callback( result );
	          } // if
	        },
	        error: function (xhr, status, er){           
				if( error ){
                	error( er );
        		} // if
        	}
        })
        .fail(function() {
			alert( "ajax 부서 삭제 실패!!!" );
		});
		
	} // remove
	
	/* [2-2] 부서 삭제
	// http://localhost/scott/dept/delete?deptno=50 + GET 요청
	function remove(deptno, callback, error){
		console.log("deptService.remove()........");
	} // remove
	*/
	
	return {
		add : add,
		remove : remove
	};

})(); // 자체 호출 함수