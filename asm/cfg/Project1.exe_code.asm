0x00401570:	subl %esp, $0x1c<UINT8>
0x00401573:	movl (%esp), $0x1<UINT32>
0x0040157a:	call __set_app_type@msvcrt.dll
__set_app_type@msvcrt.dll: API Node	
0x00401580:	call 0x00401180
0x00401180:	pushl %ebp
0x00401181:	movl %ebp, %esp
0x00401183:	pushl %edi
0x00401184:	pushl %esi
0x00401185:	pushl %ebx
0x00401186:	subl %esp, $0x5c<UINT8>
0x00401189:	movl %eax, 0x405064
0x0040118e:	testl %eax, %eax
0x00401190:	je 28
0x00401192:	movl 0x8(%esp), $0x0<UINT32>
0x0040119a:	movl 0x4(%esp), $0x2<UINT32>
0x004011a2:	movl (%esp), $0x0<UINT32>
0x004011a9:	call 0x00401700
0x00401700:	pushl %esi
0x00401701:	pushl %ebx
0x00401702:	subl %esp, $0x14<UINT8>
0x00401705:	cmpl 0x407040, $0x2<UINT8>
0x0040170c:	movl %eax, 0x24(%esp)
0x00401710:	je 10
0x00401712:	movl 0x407040, $0x2<UINT32>
0x0040171c:	cmpl %eax, $0x2<UINT8>
0x0040171f:	je 0x00401733
0x00401733:	movl %esi, $0x409014<UINT32>
0x00401738:	subl %esi, $0x409014<UINT32>
0x0040173e:	sarl %esi, $0x2<UINT8>
0x00401741:	testl %esi, %esi
0x00401743:	jle 0x00401726
0x00401726:	addl %esp, $0x14<UINT8>
0x00401729:	movl %eax, $0x1<UINT32>
0x0040172e:	popl %ebx
0x0040172f:	popl %esi
0x00401730:	ret $0xc<UINT16>

0x004011ab:	subl %esp, $0xc<UINT8>
0x004011ae:	movl (%esp), $0x401000<UINT32>
0x004011b5:	call 0x004036d8
0x004036d8:	jmp SetUnhandledExceptionFilter@kernel32.dll
SetUnhandledExceptionFilter@kernel32.dll: API Node	
0x004011ba:	subl %esp, $0x4<UINT8>
0x004011bd:	call 0x004017b0
0x004017b0:	pushfl
0x004017b1:	pushfl
0x004017b2:	popl %eax
0x004017b3:	movl %edx, %eax
0x004017b5:	xorl %eax, $0x200000<UINT32>
0x004017ba:	pushl %eax
0x004017bb:	popfl
0x004017bc:	pushfl
0x004017bd:	popl %eax
0x004017be:	popfl
0x004017bf:	xorl %eax, %edx
0x004017c1:	testl %eax, $0x200000<UINT32>
0x004017c6:	je 0x00401871
0x00401871:	rep ret

0x004011c2:	call 0x004017a0
0x004017a0:	fninit
0x004017a2:	ret

0x004011c7:	movl %eax, 0x404000
0x004011cc:	testb %al, $0x2<UINT8>
0x004011ce:	je 829
0x004011d4:	call 0x004036e0
0x004036e0:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004011d9:	orl %ecx, $0xffffffff<UINT8>
0x004011dc:	movl -76(%ebp), %esp
0x004011df:	movl %esi, %eax
0x004011e1:	xorl %eax, %eax
0x004011e3:	movl %edi, %esi
0x004011e5:	repn scasb %al, %es:(%edi)
0x004011e7:	notl %ecx
0x004011e9:	leal %eax, 0xf(%ecx,%ecx)
0x004011ed:	andl %eax, $0xfffffff0<UINT8>
0x004011f0:	call 0x00401e90
0x00401e90:	pushl %ecx
0x00401e91:	pushl %eax
0x00401e92:	cmpl %eax, $0x1000<UINT32>
0x00401e97:	leal %ecx, 0xc(%esp)
0x00401e9b:	jb 0x00401eb2
0x00401eb2:	subl %ecx, %eax
0x00401eb4:	orl (%ecx), $0x0<UINT8>
0x00401eb7:	popl %eax
0x00401eb8:	popl %ecx
0x00401eb9:	ret

0x004011f5:	subl %esp, %eax
0x004011f7:	leal %eax, 0x14(%esp)
0x004011fb:	movl %edx, %eax
0x004011fd:	movl -72(%ebp), %eax
0x00401200:	movl %eax, 0x404000
0x00401205:	movl -28(%ebp), $0x0<UINT32>
0x0040120c:	movl -60(%ebp), $0x0<UINT32>
0x00401213:	movl -64(%ebp), $0x0<UINT32>
0x0040121a:	andl %eax, $0x40<UINT8>
0x0040121d:	cmpl %eax, $0x1<UINT8>
0x00401220:	sbbl %eax, %eax
0x00401222:	movl -68(%ebp), %eax
0x00401225:	xorl %eax, %eax
0x00401227:	andl -68(%ebp), $0xffffc000<UINT32>
0x0040122e:	addl -68(%ebp), $0x4010<UINT32>
0x00401235:	addl %esi, $0x1<UINT8>
0x00401238:	movzbl %ebx, -1(%esi)
0x0040123c:	movsbl %ecx, %bl
0x0040123f:	testl %ecx, %ecx
0x00401241:	je 0x00401336
0x00401247:	cmpb %bl, $0x3f<UINT8>
0x0040124a:	je 688
0x00401250:	jg 0x004012c0
0x00401252:	cmpb %bl, $0x27<UINT8>
0x00401255:	je 605
0x0040125b:	cmpb %bl, $0x2a<UINT8>
0x0040125e:	nop
0x00401260:	je 666
0x00401266:	cmpb %bl, $0x22<UINT8>
0x00401269:	jne 0x00401422
0x0040126f:	movl %ebx, %eax
0x00401271:	sarl %ebx
0x00401273:	je 0x00401556
0x00401556:	movl %ebx, %edx
0x00401558:	jmp 0x0040128b
0x0040128b:	testb %al, $0x1<UINT8>
0x0040128d:	jne 22
0x0040128f:	cmpl -60(%ebp), $0x27<UINT8>
0x00401293:	je 16
0x00401295:	xorl -60(%ebp), %ecx
0x00401298:	movl %edx, %ebx
0x0040129a:	xorl %eax, %eax
0x0040129c:	movl -64(%ebp), $0x1<UINT32>
0x004012a3:	jmp 0x00401235
0x004012c0:	cmpb %bl, $0x5c<UINT8>
0x004012c3:	je 0x004014b0
0x004012c9:	cmpb %bl, $0x7f<UINT8>
0x004012cc:	je 558
0x004012d2:	cmpb %bl, $0x5b<UINT8>
0x004012d5:	jne 0x00401422
0x00401422:	testl %eax, %eax
0x00401424:	leal %edi, (%edx,%eax)
0x00401427:	je 0x0040154f
0x0040154f:	movl %edi, %edx
0x00401551:	jmp 0x0040143b
0x0040143b:	movl %eax, -60(%ebp)
0x0040143e:	testl %eax, %eax
0x00401440:	jne 0x00401495
0x00401495:	leal %edx, 0x1(%edi)
0x00401498:	xorl %eax, %eax
0x0040149a:	movb (%edi), %bl
0x0040149c:	jmp 0x00401235
0x004014b0:	addl %eax, $0x1<UINT8>
0x004014b3:	jmp 0x00401235
0x0040142d:	leal %esi, (%esi)
0x00401430:	addl %edx, $0x1<UINT8>
0x00401433:	cmpl %edx, %edi
0x00401435:	movb -1(%edx), $0x5c<UINT8>
0x00401439:	jne -11
0x00401336:	testl %eax, %eax
0x00401338:	je 0x0040155d
0x0040155d:	movl %eax, %edx
0x0040155f:	nop
0x00401560:	jmp 0x0040134b
0x0040134b:	cmpl -64(%ebp), $0x0<UINT8>
0x0040134f:	jne 0x00401356
0x00401356:	movb (%eax), $0x0<UINT8>
0x00401359:	leal %eax, -40(%ebp)
0x0040135c:	movl 0xc(%esp), %eax
0x00401360:	movl 0x8(%esp), $0x0<UINT32>
0x00401368:	movl %eax, -68(%ebp)
0x0040136b:	movl 0x4(%esp), %eax
0x0040136f:	movl %eax, -72(%ebp)
0x00401372:	movl (%esp), %eax
0x00401375:	call 0x00402a70
0x00402a70:	pushl %ebp
0x00402a71:	movl %ebp, %esp
0x00402a73:	pushl %edi
0x00402a74:	pushl %esi
0x00402a75:	pushl %ebx
0x00402a76:	subl %esp, $0x1c<UINT8>
0x00402a79:	movl %esi, 0x14(%ebp)
0x00402a7c:	movl %ebx, 0x8(%ebp)
0x00402a7f:	cmpl (%esi), $0x405116<UINT32>
0x00402a85:	je 13
0x00402a87:	movl %eax, %esi
0x00402a89:	call 0x004020d0
0x004020d0:	pushl %ebp
0x004020d1:	pushl %edi
0x004020d2:	pushl %esi
0x004020d3:	movl %esi, %eax
0x004020d5:	pushl %ebx
0x004020d6:	subl %esp, $0x1c<UINT8>
0x004020d9:	testl %eax, %eax
0x004020db:	je 71
0x004020dd:	movl %eax, 0xc(%eax)
0x004020e0:	leal %edi, 0x1(%eax)
0x004020e3:	leal %ebp, (,%edi,4)
0x004020ea:	movl (%esp), %ebp
0x004020ed:	call 0x00403668
0x00403668:	jmp malloc@msvcrt.dll
malloc@msvcrt.dll: API Node	
0x004020f2:	movl %ebx, %eax
0x004020f4:	testl %ebx, %ebx
0x004020f6:	movl 0x8(%esi), %eax
0x004020f9:	movl %eax, $0x3<UINT32>
0x004020fe:	je 0x00402126
0x00402100:	testl %edi, %edi
0x00402126:	addl %esp, $0x1c<UINT8>
0x00402129:	popl %ebx
0x0040212a:	popl %esi
0x0040212b:	popl %edi
0x0040212c:	popl %ebp
0x0040212d:	ret

0x00402a8e:	movl (%esi), $0x405116<UINT32>
0x00402a94:	movl (%esp), %esi
0x00402a97:	movl %ecx, 0x10(%ebp)
0x00402a9a:	movl %eax, %ebx
0x00402a9c:	movl %edx, 0xc(%ebp)
0x00402a9f:	call 0x00402480
0x00402480:	pushl %ebp
0x00402481:	movl %ebp, %esp
0x00402483:	pushl %edi
0x00402484:	pushl %esi
0x00402485:	pushl %ebx
0x00402486:	movl %ebx, %eax
0x00402488:	subl %esp, $0x6c<UINT8>
0x0040248b:	movl -48(%ebp), %edx
0x0040248e:	movl -88(%ebp), %ecx
0x00402491:	movl (%esp), %eax
0x00402494:	call 0x00403680
0x00403680:	jmp strlen@msvcrt.dll
strlen@msvcrt.dll: API Node	
0x00402499:	leal %edx, 0x1(%eax)
0x0040249c:	addl %eax, $0x10<UINT8>
0x0040249f:	andl %eax, $0xfffffff0<UINT8>
0x004024a2:	call 0x00401e90
0x00401e9d:	subl %ecx, $0x1000<UINT32>
0x004024a7:	subl %esp, %eax
0x004024a9:	leal %eax, 0xc(%esp)
0x004024ad:	movl 0x8(%esp), %edx
0x004024b1:	movl 0x4(%esp), %ebx
0x004024b5:	movl (%esp), %eax
0x004024b8:	call 0x00403650
0x00403650:	jmp memcpy@msvcrt.dll
memcpy@msvcrt.dll: API Node	
0x00403656:	nop
0x00403657:	nop
0x00403658:	jmp calloc@msvcrt.dll
calloc@msvcrt.dll: API Node	
0x0040365e:	nop
0x0040365f:	nop
0x00403660:	jmp free@msvcrt.dll
free@msvcrt.dll: API Node	
0x00403666:	nop
0x00403667:	nop
0x0040366e:	nop
0x0040366f:	nop
0x00403670:	jmp tolower@msvcrt.dll
tolower@msvcrt.dll: API Node	
0x00403676:	nop
0x00403677:	nop
0x00403678:	jmp realloc@msvcrt.dll
realloc@msvcrt.dll: API Node	
0x0040367e:	nop
0x0040367f:	nop
0x00403686:	nop
0x00403687:	nop
0x00403688:	jmp strcoll@msvcrt.dll
strcoll@msvcrt.dll: API Node	
0x0040368e:	nop
0x0040368f:	nop
0x00403690:	jmp _errno@msvcrt.dll
_errno@msvcrt.dll: API Node	
0x00403696:	nop
0x00403697:	nop
0x00403698:	jmp setlocale@msvcrt.dll
setlocale@msvcrt.dll: API Node	
0x0040369e:	nop
0x0040369f:	nop
0x004036a0:	jmp wcstombs@msvcrt.dll
wcstombs@msvcrt.dll: API Node	
0x004036a6:	nop
0x004036a7:	nop
0x004036a8:	jmp mbstowcs@msvcrt.dll
mbstowcs@msvcrt.dll: API Node	
0x004036ae:	nop
0x004036af:	nop
0x004036b0:	jmp _fullpath@msvcrt.dll
_fullpath@msvcrt.dll: API Node	
0x004036b6:	nop
0x004036b7:	nop
0x004036b8:	jmp _findfirst@msvcrt.dll
_findfirst@msvcrt.dll: API Node	
0x004036be:	nop
0x004036bf:	nop
0x004036c0:	jmp strncpy@msvcrt.dll
strncpy@msvcrt.dll: API Node	
0x004036c6:	nop
0x004036c7:	nop
0x004036c8:	jmp _findnext@msvcrt.dll
_findnext@msvcrt.dll: API Node	
0x004036ce:	nop
0x004036cf:	nop
0x004036d0:	jmp _findclose@msvcrt.dll
_findclose@msvcrt.dll: API Node	
0x004036d6:	nop
0x004036d7:	nop
0x004036de:	nop
0x004036df:	nop
0x004036e6:	nop
0x004036e7:	nop
0x004036e8:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
0x00401ea3:	orl (%ecx), $0x0<UINT8>
0x00401ea6:	subl %eax, $0x1000<UINT32>
0x00401eab:	cmpl %eax, $0x1000<UINT32>
0x00401eb0:	ja 0x00401e9d
0x00402102:	movl %edx, %edi
0x00402104:	movl 0x4(%esi), $0x0<UINT32>
0x0040210b:	jle 23
0x0040210d:	leal %ecx, -4(%ebp)
0x00402110:	jmp 0x00402115
0x00402115:	movl (%ebx,%ecx), $0x0<UINT32>
0x0040211c:	subl %ecx, $0x4<UINT8>
0x0040211f:	subl %edx, $0x1<UINT8>
0x00402122:	jne -18
0x00402124:	xorl %eax, %eax
