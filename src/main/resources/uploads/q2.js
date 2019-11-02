db.getCollection("Movie").aggregate(

	// Pipeline
	[
		// Stage 1
		{
			$unwind: {
			    path : "$genres",
			    includeArrayIndex : "arrayIndex", // optional
			    preserveNullAndEmptyArrays : false // optional
			}
		},

		// Stage 2
		{
			$match: {
			 $and : [{type : {$eq : "movie" }}, {genres : {$eq : "Sci-Fi" }}]
			
			}
		},

		// Stage 3
		{
			$unwind: {
			    path : "$members",
			    includeArrayIndex : "arrayIndex", // optional
			    preserveNullAndEmptyArrays : false // optional
			}
		},

		// Stage 4
		{
			$group: {
			_id : "$members",
			count : {$sum : 1},
			from : {$push : "$_id"}
			}
		},

		// Stage 5
		{
			$match: {
			  count : {$gte : 5}
			
			}
		},

		// Stage 6
		{
			$project: {
			    _id : 0 ,
			    itemset : ["$_id"],
			    count : 1,
			    from : 1
			}
		},

		// Stage 7
		{
			$out: "L"
		},

	]

	// Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

);
