<html>
<body>
	<h2>Jersey RESTful Web Application!</h2>
	<p>
		<a href="webapi/myresource">Jersey resource</a>
	<p>
		Visit <a href="http://jersey.java.net">Project Jersey website</a> for
		more information on Jersey!
	</p>

	<div>
		<form role="form" method="get"
			action="/webapi/initChat"
			accept-charset="utf-8" target="resultFrame1">
			<fieldset>
				<button type="submit" class="btn btn-default">Submit</button>
			</fieldset>
		</form>
		<iframe width="100%" height="50" border="0" name="resultFrame1"
			id="resultFrame1"></iframe>
	</div>



	<div>
		<form role="form" method="get"
			action="/webapi/postConversation"
			accept-charset="utf-8" target="resultFrame2">
			<fieldset>
				<label>client_id</label><input type="text" name="clientId" />
				<label>conversation_id</label><input type="text" name="conversationId" />
				 <label>message: <br></label><input type="text" name="input" value="coverage" />
				<button type="submit" class="btn btn-default">Submit</button>
			</fieldset>
		</form>
		<iframe width="100%" height="250" border="0" name="resultFrame2"
			id="resultFrame2"></iframe>
	</div>


</body>
</html>
