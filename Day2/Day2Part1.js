const fs = require('fs');
const async = require('async');

fs.readFile('./Day2.txt', 'utf8', function(err, contents) {
	if (err) {
		console.log(err);
		return;
	}

	var bytes = contents.split(",").map( value => Number(value) );
	var index = 0;

	bytes[1] = 12;
	bytes[2] = 2;

	async.whilst(function(cb) {
		return cb(null, bytes[index] != 99);
	}, function(cb) {
		if (bytes[index] == 1) {
			bytes[bytes[index + 3]] = bytes[bytes[index + 1]] + bytes[bytes[index + 2]];
		} else if (bytes[index] == 2) {
			bytes[bytes[index + 3]] = bytes[bytes[index + 1]] * bytes[bytes[index + 2]];
		}
		index += 4;
		return cb();
	}, function(err) {
		if (err) {
			console.log(err);
			return;
		}

		console.log(bytes[0])
	});
});