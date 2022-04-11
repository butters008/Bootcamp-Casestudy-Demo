<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp"/>
<div class="container">
    <h1>AJAX Example</h1>

    <script>
        $(document).ready(function () {
            $("#myRange").on("click", function () {
                let sliderValue = $("#myRange").val();
                console.log("Before ajax call")
                $.ajax({
                    url: '/ajaxRequest',
                    type: 'GET',
                    data: {
                        'sliderValue': sliderValue
                    },
                    success: function (data) {
                        console.log("success " + data)
                        console.log("After Ajax Callback")
                        $("#rangeValue1").text(data);
                    },
                    error: function (request, error) {
                        console.log("error = " + error + "  " + request)
                    }
                });
                console.log("After Ajax Call")
            });
        });
    </script>


    <div class="slidecontainer">
        <input type="range" min="1" max="100" value="50" class="slider" id="myRange">
        <div id="rangeValue"></div>
        <div id="rangeValue1"></div>
    </div>


    <script>
        var slider = document.getElementById("myRange");
        var output = document.getElementById("rangeValue");
        output.innerHTML = slider.value; // Display the default slider value
        // Update the current slider value (each time you drag the slider handle)
        slider.oninput = function () {
            output.innerHTML = this.value;
        }
    </script>

</div>


<jsp:include page="includes/footer.jsp"/>