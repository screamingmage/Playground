function clearDownAndUpdateScrumInformation(content) {
	$('div.scrum-information').html('');
	$('div.scrum-information').append(content);
}

function setupClickForViewStory() {
	$('span.view-story a').unbind('click');
	$('span.view-story a').on("click", function(e) {
		e.preventDefault();
		displayStoryDetails(this.id);
	});
}

function setupClickForBackButton() {
	$('div.story-edit a').unbind('click');
	$('div.story-edit a').on("click", function(e) {
		e.preventDefault();
		displayStoryList();
	});
}

function setupClickForCreateStoryButton() {
	$('#create-st').unbind('submit');
	$('#create-st').submit(function() {
		console.log("creating story");
		
		var story = new Object();
		story.description = $('#descriptionText').val();
		story.title = $('#titleText').val();
		
		var storyAsJson = JSON.stringify(story);
		console.log("json to be sent: " + storyAsJson);
		
		$.ajax ({
            type : 'POST',
            url : '/website-1.0/scrum/story/json',
            contentType:"application/json; charset=utf-8",
            data : storyAsJson, 
            dataType : "json",
        })
        .success(function() {
        	console.log("Successfully created story");
        	displayStoryList();
        })
		.error(function() {
        	console.log("Something went wrong in creating story");
        });
        
		return false;
	});
}

function displayStoryDetails(id) {
	$.getJSON("http://localhost:8080/website-1.0/scrum/story/"+$.trim(id), function(story) {
		
		var content = 	
			'<div class="story-details">' +
				'<div class="story-attribute"><h4>ID</h4><p>' + story.uniqueId + '</p></div>' +
				'<div class="story-attribute"><h4>Title</h4><p>' + story.title + '</p></div>' +
				'<div class="story-attribute"><h4>Description</h4><p>' + story.description + '</p></div>' +
			'</div>' +
			'<div class="story-edit">' + 
				'<a href="#">Back</a>' + 
			'</div>';
		
		// deal with subtasks
		if (story.subtasks != null) {
			$.each(story.subTasks, function(){
				console.log("subtask: " + this.uniqueId);
			});
		}
		
		clearDownAndUpdateScrumInformation(content);
		setupClickForBackButton();
		
	})
	.complete(function() {
		console.log("Completed loading story with id " + id)
	})
	.error(function() {
		clearDownAndUpdateScrumInformation(
				'<div class="error-message">' + 
					'<p>Unable to find any stories</p>' + 
				'</div>');
	});
}

function displayStoryList() {
	console.log("Updating story list");
	
	$.getJSON("http://localhost:8080/website-1.0/scrum/stories", function(stories) {
		
		var list = '<ul>';
		
		list += 
			'<li class="gap">' +
				'<span class="unique-id bold">ID</span> ' +
				'<span class="title bold">Title</span> ' +
			'</li>';
		
		$.each(stories, function() {
			list += 
				'<li>' +
					'<span class="unique-id">' + this.uniqueId + '</span> ' +
					'<span class="title">' + this.title + '</span> ' +
					'<span class="view-story"><a href="#" id="' + this.uniqueId + '">View</a></span>'
				'</li>';
		});
		
		list += '</ul>'
		
		clearDownAndUpdateScrumInformation(list);
		setupClickForViewStory();
		
	})
	.complete(function() {
		console.log("Completed loading stories")
	})
	.error(function() {
		console.log("Something went wrong when retrieving stories");
	});
}

function displayCreateStory() {
	$('div.scrum-information').load('http://localhost:8080/website-1.0/templates/create-story.html', function() {
		console.log("Completed loading create story template");
		setupClickForCreateStoryButton();
	});	
}

function addHandlers() {
	$(".load-stories").click(function(e) {
		e.preventDefault();
		displayStoryList();
	});
	
	$(".create-story").click(function(e) {
		e.preventDefault();
		displayCreateStory();
	})
	
	$(".search-box").keyup(function(e) {
		if (this.value == null || this.value == '') {
			clearDownAndUpdateScrumInformation('');
			return false;
		} else {
			displayStoryDetails(this.value);
		}
	});
}

$(document).ready(function() {
	addHandlers();
});