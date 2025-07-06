function toggleAnswer(element) {
	const answer = element.querySelector(".faq-answer");
	answer.style.display = (answer.style.display === "block") ? "none" : "block";
}