
<div class="layer">
	<img src="${ require('../img/hei.jpg') }">
	<div> this ia <%= name %> layer</div>
	<% for (var i = 0; i < arr.length; i ++) { %>
		<%= arr[i] %>
	<% } %>
</div>
