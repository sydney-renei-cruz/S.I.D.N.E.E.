<!DOCTYPE html  >
<html lang="en">
    <head>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<script src="js/main.js"></script>
		<script>
                    topBar();		
                </script>
		
		<div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"> Add Branch</h1>
				</div>
			</div>
                        <form class="form-horizontal" role="form" action = "addBranch" method = "post" style="font-size: 1.1em; font-family: 'arial' !important;" enctype="multipart/form-data">
			<input type="hidden" name="submitted" value="true">
                        <div class="row">
				<!-- left column -->
				<div class="col-md-3">
					<div class="text-center">
						<img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
						<h6 style="font-family: arial !important;">Upload product photo...</h6>
						<input type="file" class="form-control" name="image" required>
					</div>
				</div>
				<div class="col-md-9 personal-info">
					<div class="alert alert-info alert-dismissable">
						<a class="panel-close close" data-dismiss="alert">x</a> 
						<i class="fa fa-coffee"></i>
							<strong> Everything but image </strong> is required.
					</div>
					<h3>Branch Info </h3>
                                                <div class="form-group">
							<label class="col-lg-3 control-label">Branch Number:</label>
							<div class="col-lg-8">
								<input class="form-control" type="number" step="1" name="branchNum" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Name:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" name="branchName" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Address:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" name="branchAddress" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Branch Phone Number:</label>
							<div class="col-lg-8">
								<input class="form-control" type="text" name="branchPhoneNum" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
                                                            <input type="submit" class="btn btn-primary" value="Submit">
                                                            <span></span>
                                                            <input type="submit" class="btn btn-primary" name="add" value="Add Another">
                                                            <span></span>
                                                            <input type="reset" class="btn btn-default" value="Cancel">
							</div>
						</div>
					</form>
				</div>			
			</div>
		</div>
		<span id="top-link-block" class="hidden">
    <a href="#top" class="well well-sm"  onclick="$('html,body').animate({scrollTop:0},'slow');return false;">
        <i class="glyphicon glyphicon-chevron-up"></i>
    </a>
</span><!-- /top-link-block -->
        <script>
			// Only enable if the document has a long scroll bar
			// Note the window height + offset
			if ( ($(window).height() + 100) < $(document).height() ) {
				$('#top-link-block').removeClass('hidden').affix({
			// how far to scroll down before link "slides" into view
				offset: {top:100}
				});
			}
		</script>
    </body>
</html>