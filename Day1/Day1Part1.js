const fs = require('fs');
const async = require('async');

fs.readFile('./Day1.txt', 'utf8', function(err, contents) {
	if (err) {
		console.log(err);
		return;
	}

	const values = contents.split("\n");
	var total = 0;

	async.forEach(values, function(value, cb) {
		if (value) {
			total += (Math.floor(value / 3) - 2);
		}
		return cb(null)
	}, function(err) {
		if (err) {
			console.log(err);
			return;
		}

		console.log(total);
	});
});
