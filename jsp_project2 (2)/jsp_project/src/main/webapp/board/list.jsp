<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List Page</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1>Board List Page</h1>
<!-- 검색라인 -->
<form action="/brd/page" method="post">
   <div>
      <c:set value="${pgh.pgvo.type }" var="typed"></c:set>
      <select name="type">
      <!-- selected : 현재 내가 선택한 값 -->
         <option ${typed == null? 'selected':'' }>Choose</option>
         <option value="t" ${typed eq 't' ? 'seleted':'' }>title</option>
         <option value="c" ${typed eq 'c' ? 'seleted':'' }>content</option>
         <option value="w" ${typed eq 'w' ? 'seleted':'' }>writer</option>
         <option value="tc" ${typed eq 'tc' ? 'seleted':'' }>title or content</option>
         <option value="tw" ${typed eq 'tw' ? 'seleted':'' }>title or writer</option>
         <option value="cw" ${typed eq 'cw' ? 'seleted':'' }>content or writer</option>
      </select>
      <input type="text" name="keyword" placeholder="Search">
      <input type="hidden" name="pageNo" value="${pgh.pgvo.pageNo }">
      <input type="hidden" name="qty" value="${pgh.pgvo.qty }">
      <button type="submit" class="btn btn-primary position-relative">
  Search
  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    99+
    <span class="visually-hidden">unread messages</span>
  </span>
</button>
      
   </div>
</form>

<table border="1">
<tr>
	<th>bno</th>
	<th>title</th>
	<th>writer</th>
	<th>reg_date</th>
	<th>read_count</th>
</tr>
<c:forEach items="${list }" var="bvo">
<tr>
	<td>${bvo.bno }</td>
	<td>
	<c:if test="${bvo.image_file ne null }">
	<img alt="없음" src="/_fileUpload/th_${bvo.image_file }">
	</c:if>
	<a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a>
	</td>
	<td>${bvo.writer }</td>
	<td>${bvo.reg_date }</td>
	<td>${bvo.read_count }</td>
</tr>
</c:forEach>
</table>
<a href="/"><button>index</button></a> <br>
<a href="/brd/my"><button>My List</button></a>

<!-- 페이지네이션 위치 -->
<!-- 컨트롤러에서 page 정보를 싣고 와야함.  -->
<!-- startpage~endpage까지 숫자 반복 foreach -->
<!-- 이전페이지 -->
<c:if test="${pgh.prev }">
	<a href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ◁ </a>
</c:if>
<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
	<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } | </a>
</c:forEach>
<!-- 다음페이지 -->
<c:if test="${pgh.next }">
	<a href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ▷ </a>
</c:if>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>