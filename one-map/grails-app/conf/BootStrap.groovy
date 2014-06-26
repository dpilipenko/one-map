import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User

class BootStrap {

    def init = { servletContext ->
		
		
		new Hotspot( floor: "17", polygon: "M327.276,443.556 337.835,489.156 414.755,470.916 403.835,425.436z").save(flush: true);
		new Hotspot( floor: "17", polygon: "M435.996,384.636 487.835,372.996 508.317,448.939 453.276,461.907z").save(flush: true);
		new Hotspot( floor: "17", polygon: "M50.996,300.876 481.295,348.391 429.755,360.516 428.196,365.436 389.076,370.116 368.316,321.516z").save(flush: true);
		new Hotspot( floor: "17", polygon: "M264.156,169.956 262.355,225.036 214.956,228.516 182.406,222.367 175.116,219.156 161.979,210.562 165.036,195.876 169.835,172.716 170.315,170.796z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.618,228.271l-9.731-5.594c0,0-3.673,6.887-0.494,12.104 c3.179,5.218,10.403,4.622,10.403,4.622L189.618,228.271z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.618,228.271l9.468-5.909c0,0-4.278-7.164-10.38-6.838 c-6.101,0.326-8.819,7.153-8.819,7.153L189.618,228.271z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.796,239.403l-0.178-11.133l9.468-5.909 c0,0,3.462,7.974,1.08,11.919C197.784,238.226,189.796,239.403,189.796,239.403z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.265,251.08l-9.84,5.399c0,0,3.97,6.72,10.08,6.72 s9.36-6.479,9.36-6.479L189.265,251.08z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.265,251.08l-0.12-11.16c0,0-8.345-0.074-11.24,5.307 c-2.896,5.38,1.521,11.253,1.521,11.253L189.265,251.08z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.435,194.845l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L189.435,194.845z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.435,194.845l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L189.435,194.845z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M179.721,200.286l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C184.549,206.757,179.721,200.286,179.721,200.286z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M153.04,184.165l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L153.04,184.165z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M143.326,189.606l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C148.154,196.077,143.326,189.606,143.326,189.606z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M104.938,186.498l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L104.938,186.498z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M104.938,186.498l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L104.938,186.498z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M95.223,191.939l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C100.051,198.41,95.223,191.939,95.223,191.939z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M86.44,141.085l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L86.44,141.085z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M86.44,141.085l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L86.44,141.085z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M76.726,146.526l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C81.554,152.997,76.726,146.526,76.726,146.526z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M105.163,152.623l-9.573-5.862c0,0-3.862,6.783-0.829,12.086 c3.033,5.303,10.271,4.909,10.271,4.909L105.163,152.623z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M105.163,152.623l9.628-5.644c0,0-4.078-7.281-10.187-7.124 c-6.107,0.157-9.015,6.906-9.015,6.906L105.163,152.623z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M105.032,163.756l0.131-11.134l9.628-5.644 c0,0,3.24,8.066,0.749,11.944C113.049,162.8,105.032,163.756,105.032,163.756z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M124.455,141.324l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L124.455,141.324z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M124.455,141.324l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L124.455,141.324z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M114.741,146.766l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C119.569,153.236,114.741,146.766,114.741,146.766z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.435,102.145l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L189.435,102.145z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M179.721,107.586l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C184.549,114.057,179.721,107.586,179.721,107.586z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M179.721,107.586l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C184.549,114.057,179.721,107.586,179.721,107.586z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M188.683,172.079l-9.573-5.862c0,0-3.862,6.783-0.829,12.086 c3.033,5.303,10.271,4.91,10.271,4.91L188.683,172.079z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M188.683,172.079l9.628-5.644c0,0-4.078-7.281-10.187-7.124 c-6.107,0.157-9.015,6.906-9.015,6.906L188.683,172.079z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M188.552,183.213l0.131-11.134l9.628-5.644 c0,0,3.24,8.066,0.749,11.944C196.569,182.256,188.552,183.213,188.552,183.213z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M125.042,197.895l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L125.042,197.895z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M124.91,209.029l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C132.927,208.072,124.91,209.029,124.91,209.029z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M86.765,197.914l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L86.765,197.914z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M86.765,197.914l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L86.765,197.914z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M86.633,209.049l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C94.65,208.092,86.633,209.049,86.633,209.049z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M152.844,157.574l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L152.844,157.574z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M152.844,157.574l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L152.844,157.574z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M152.713,168.709l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C160.73,167.752,152.713,168.709,152.713,168.709z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M190.082,136.155l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L190.082,136.155z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M190.082,136.155l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L190.082,136.155z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.95,147.29l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C197.967,146.333,189.95,147.29,189.95,147.29z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M52.285,153.734l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L52.285,153.734z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M52.285,153.734l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L52.285,153.734z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M52.153,164.869l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C60.17,163.912,52.153,164.869,52.153,164.869z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M190.082,80.294l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L190.082,80.294z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M189.95,91.429l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C197.967,90.472,189.95,91.429,189.95,91.429z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M191.343,40.877l5.924-9.535c0,0-6.758-3.906-12.08-0.909 c-5.323,2.998-4.978,10.238-4.978,10.238L191.343,40.877z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M191.343,40.877l5.58,9.665c0,0,7.307-4.029,7.191-10.139 c-0.116-6.108-6.847-9.06-6.847-9.06L191.343,40.877z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M180.209,40.672l11.134,0.205l5.58,9.665 c0,0-8.086,3.187-11.949,0.67C181.113,48.695,180.209,40.672,180.209,40.672z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M33.7,142.765l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L33.7,142.765z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M33.7,142.765l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L33.7,142.765z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M23.986,148.206l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C28.814,154.677,23.986,148.206,23.986,148.206z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M57.555,185.965l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L57.555,185.965z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M57.555,185.965l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L57.555,185.965z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M47.84,191.406l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C52.668,197.877,47.84,191.406,47.84,191.406z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M302.481,371.038l-11.849-3.96c0,0-2.491,8.321,2.126,13.313 c4.618,4.993,12.367,2.753,12.367,2.753L302.481,371.038z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M302.481,371.038l9.026-8.53c0,0-6.245-6.876-12.829-5.175 c-6.585,1.698-8.046,9.745-8.046,9.745L302.481,371.038z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M305.126,383.145l-2.645-12.106l9.026-8.53 c0,0,5.531,7.936,3.802,12.763C313.58,380.101,305.126,383.145,305.126,383.145z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M416.579,328.285l-2.595-11.981c0,0-8.271,2.069-10.168,8.465 c-1.898,6.4,3.875,11.814,3.875,11.814L416.579,328.285z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M416.579,328.285l11.722,3.342c0,0,2.672-8.713-2.062-13.417 c-4.732-4.703-12.254-1.906-12.254-1.906L416.579,328.285z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M407.691,336.583l8.888-8.298l11.722,3.342 c0,0-3.924,8.645-8.863,9.604C414.495,342.193,407.691,336.583,407.691,336.583z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M348.948,360.123l9.479-8.453c0,0-6.086-6.401-12.821-4.849 c-6.74,1.554-8.676,9.521-8.676,9.521L348.948,360.123z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M348.948,360.123l2.967,12.272c0,0,7.982-2.324,9.805-8.995 c1.826-6.666-3.293-11.73-3.293-11.73L348.948,360.123z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M336.93,356.343l12.019,3.78l2.967,12.272 c0,0-9.795,0.875-13.182-3.089C335.345,365.341,336.93,356.343,336.93,356.343z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M298.398,407.67l12.133-2.303c0,0-1.871-8.384-8.263-10.458 c-6.396-2.078-11.998,3.596-11.998,3.596L298.398,407.67z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M298.398,407.67l-3.668,11.718c0,0,8.707,2.916,13.566-1.731 c4.858-4.643,2.234-12.289,2.234-12.289L298.398,407.67z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M290.271,398.505l8.128,9.165l-3.668,11.718 c0,0-8.604-4.172-9.444-9.174C284.447,405.214,290.271,398.505,290.271,398.505z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M326.134,401.694l8.488,9.903c0,0,6.695-6.122,5.234-13.068 c-1.457-6.951-9.602-9.1-9.602-9.1L326.134,401.694z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M326.134,401.694l-12.664,2.8c0,0,1.906,9.509,8.719,11.517 c6.809,2.009,12.434-4.413,12.434-4.413L326.134,401.694z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M330.255,389.43l-4.121,12.265l-12.664,2.8 c0,0-0.7-10.075,3.44-13.474C321.051,387.621,330.255,389.43,330.255,389.43z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M381.526,386.92l12.367-2.312c0,0-1.908-7.437-8.413-9.568 c-6.511-2.137-12.207,2.52-12.207,2.52L381.526,386.92z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M381.526,386.92l-3.771,11.928c0,0,8.862,2.995,13.824-1.726 c4.965-4.716,2.313-12.514,2.313-12.514L381.526,386.92z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M373.273,377.56l8.253,9.36l-3.771,11.928 c0,0-8.754-4.276-9.595-9.373C367.321,384.376,373.273,377.56,373.273,377.56z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M408.106,381.349l8.289,9.48c0,0,6.406-5.971,4.933-12.663 c-1.475-6.698-9.358-8.695-9.358-8.695L408.106,381.349z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M408.106,381.349l-12.196,2.825c0,0,2.053,8.014,8.649,9.887 c6.592,1.876,11.836-3.231,11.836-3.231L408.106,381.349z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M411.97,369.471l-3.863,11.878l-12.196,2.825 c0,0-0.773-9.722,3.189-13.041C403.065,367.812,411.97,369.471,411.97,369.471z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M396.248,346.944l2.731,11.938c0,0,8.238-2.164,10.058-8.575 c1.822-6.415-4.008-11.756-4.008-11.756L396.248,346.944z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M396.248,346.944l-11.748-3.2c0,0-2.566,8.735,2.219,13.379 c4.781,4.642,12.261,1.759,12.261,1.759L396.248,346.944z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M405.029,338.551l-8.781,8.394l-11.748-3.2 c0,0,3.816-8.681,8.741-9.698S405.029,338.551,405.029,338.551z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M322.668,351.872l-2.594-11.979c0,0-8.269,2.068-10.167,8.463 c-1.897,6.398,3.875,11.812,3.875,11.812L322.668,351.872z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M322.668,351.872l11.721,3.341c0,0,2.671-8.712-2.062-13.415 c-4.731-4.702-12.252-1.904-12.252-1.904L322.668,351.872z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M313.782,360.169l8.886-8.297l11.721,3.341 c0,0-3.924,8.644-8.862,9.604C320.585,365.777,313.782,360.169,313.782,360.169z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M443.371,335.936l2.778,11.721c0,0,8.377-2.124,10.227-8.419 c1.853-6.3-4.075-11.545-4.075-11.545L443.371,335.936z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M443.371,335.936l-11.945-3.143c0,0-2.609,8.577,2.256,13.137 c4.862,4.56,12.468,1.727,12.468,1.727L443.371,335.936z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M452.301,327.692l-8.93,8.243l-11.945-3.143 c0,0,3.881-8.523,8.889-9.523C445.321,322.27,452.301,327.692,452.301,327.692z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M414.1,421.138l4.451,9.182l11.495,1.038l6.996-8.332 c0,0-7.624-3.794-11.864-4.429C420.938,417.961,414.1,421.138,414.1,421.138z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M467.117,406.87l3.873,10.108l11.495,1.038l6.449-8.07 c0,0-7.077-4.056-11.317-4.69C473.377,404.62,467.117,406.87,467.117,406.87z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M413.14,440.098l5.94-9.73l-4.98-9.229l-10.8-1.319 c0,0,0.979,8.459,2.76,12.359S413.14,440.098,413.14,440.098z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M480.94,438.34l6.36-8.76l-5.194-11.233l-10.806-1.247 c0,0,0.779,9.42,2.56,13.32S480.94,438.34,480.94,438.34z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M428.56,450.237l6.36-8.76l-5.4-10.2l-10.8-1.319 c0,0,0.979,8.459,2.76,12.359S428.56,450.237,428.56,450.237z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M464.94,425.859l6.36-8.76l-4.184-10.229l-11.977-0.967 c0,0,0.939,8.136,2.72,12.036S464.94,425.859,464.94,425.859z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M503.263,439.809l-4.687-9.291l-11.53-0.517l-6.105,8.339 c0,0,7.28,3.746,11.545,4.188S503.263,439.809,503.263,439.809z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M450.28,451.6l-3.771-9.224l-11.53-0.517l-6.419,8.378 c0,0,7.594,3.707,11.858,4.149S450.28,451.6,450.28,451.6z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M453.121,432.408l-6.441,9.251l4.449,9.63l10.711,1.905 c0,0-0.516-8.5-2.081-12.491C458.194,436.711,453.121,432.408,453.121,432.408z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M488.935,409.946l-6.828,8.4l5.194,11.233l10.859,0.54 c0,0-1.022-7.888-2.588-11.879C494.008,414.249,488.935,409.946,488.935,409.946z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M436.6,422.92l-6.633,8.025l5.012,10.914l11.53,0.517 c0,0-1.511-7.545-3.076-11.536C441.868,426.848,436.6,422.92,436.6,422.92z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M504.4,420.76l-5.974,8.568l4.836,10.48l10.636,0.871 c0,0-0.44-7.466-2.006-11.457C510.328,425.23,504.4,420.76,504.4,420.76z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M219.88,30.52 263.2,30.52 263.2,57.28 220.96,57.28z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M221,58.49 263.2,58.72 263.2,85.24 224.32,85.12z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M224.32,86.86 263.2,86.32 263.2,112.84 220.3,112.55z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M220,114.28 263.2,113.68 263.2,168.16 220,168.16 224.13,142.72z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M220,170.2 263.2,169.66 263.2,224.8 220,224.8 224.299,197.44z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M220,226 263.2,226.72 263.2,252.04 224.68,253z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M224.68,253.96 263.073,253.84 263.2,279.881 220,279.28z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M217.38,283.96 263.18,283.96 264.64,348.28 242.08,352.84 233.56,355.6z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M317.2,451.48 326.2,489.52 350.8,483.598 340.36,441.04z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M368.44,439.48 377.44,477.4 402.52,471.4 392.561,427.96z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M367.526,439.55 376.615,477.76 351.641,483.598 341.306,440.409z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M316.12,451.72 325.12,489.76 272.08,502.359 259.36,449.56 272.44,446.44 283.24,444.16 285.838,453.939 292.72,452.56z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M3207.16,460.36l13.08,54.066l51.3-12.067l-14.46-62.039l2.76-8.641l-9.66-3.239 c0,0-20.6-5.111-21.9-4.2L213.4,458.8L207.16,460.36z").save(flush: true);
		new Hotspot( floor: "11", polygon: "M219.88,4.96 263.20,4.96 263.20,29.32 219.88,29.32 219.88,4.96z").save(flush: true);


	
    }
    def destroy = {
    }
}
