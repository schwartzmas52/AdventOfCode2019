const fs = require('fs');
const async = require('async');

fs.readFile('./Day2.txt', 'utf8', function(err, contents) {
	if (err) {
		console.log(err);
		return;
	}

	var noun = 0;
	var verb = 0;

	async.whilst(function(cb) {
	  return cb(null, noun < 100);  
	}, function(nounCb) {
		async.whilst(function(cb) {
			return cb(null, verb < 100);  
		}, function(verbCb) {

			var bytes = contents.split(",").map( value => Number(value) );
			var index = 0;

			bytes[1] = noun;
			bytes[2] = verb;
			
			async.whilst(function(cb) {
				return cb(null, bytes[index] != 99);
			}, function(cb) {
				if (bytes[index] == 1) {
					bytes[bytes[index + 3]] = bytes[bytes[index + 1]] + bytes[bytes[index + 2]];
				} else if (bytes[index] == 2) {
					bytes[bytes[index + 3]] = bytes[bytes[index + 1]] * bytes[bytes[index + 2]];
				}
				index += 4;
				return process.nextTick(cb);
			}, function(err) {
				if (err) {
					console.log(err);
					return;
				}

				if (bytes[0] == 19690720) {
					console.log(100 * noun + verb);
				}

				verb++;
				return process.nextTick(verbCb);
			});

		}, function(err) {
			if (err) {
				console.log(err);
				return;
			}

			verb = 0;
			noun++;
			return process.nextTick(nounCb);
		});

	}, function(err) {
		if (err) {
			console.log(err);
			return;
		}

		console.log("done");
	});
});