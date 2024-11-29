//타임리프 body 불러오기
function loadClickEvent(elementId) {
    document.getElementById(elementId).addEventListener('click', function() {
		$('body').css('overflow', 'auto'); // 스크롤 복구
        const mainContent = document.getElementById('mainContent');
        fetch('/secure/admin/'+elementId) // Fetch the content from the server
            .then(response => response.text())
            .then(html => {
              mainContent.innerHTML = html; // Update the main content
				// 동적으로 추가된 <script> 태그 실행
              const scripts = mainContent.querySelectorAll('script');
              scripts.forEach(script => {
                  const newScript = document.createElement('script');
                  if (script.src) {
                      // 외부 스크립트의 경우 src를 복사하여 새로 추가
                      newScript.src = script.src;
                      newScript.async = true; // 비동기 로드
                  } else {
                      // 인라인 스크립트의 경우 내용을 복사하여 실행
                      newScript.textContent = script.innerHTML;
                  }
                  document.body.appendChild(newScript); // 스크립트 실행
                  script.remove(); // 중복 실행 방지
              });
          })
          .catch(err => console.error('Error loading content:', err));
    });
}

function loadBodyForm(elementId) {
	$('body').css('overflow', 'auto'); // 스크롤 복구
     const mainContent = document.getElementById('mainContent');
     fetch('/secure/admin/'+elementId) // Fetch the content from the server
         .then(response => response.text())
         .then(html => {
			mainContent.innerHTML = html; // Update the main content
					// 동적으로 추가된 <script> 태그 실행
			        const scripts = mainContent.querySelectorAll('script');
			        scripts.forEach(script => {
			            const newScript = document.createElement('script');
			            if (script.src) {
			                // 외부 스크립트의 경우 src를 복사하여 새로 추가
			                newScript.src = script.src;
			                newScript.async = true; // 비동기 로드
			            } else {
			                // 인라인 스크립트의 경우 내용을 복사하여 실행
			                newScript.textContent = script.innerHTML;
			            }
			            document.body.appendChild(newScript); // 스크립트 실행
			            script.remove(); // 중복 실행 방지
			        });
			    })
			    .catch(err => console.error('Error loading content:', err));
}